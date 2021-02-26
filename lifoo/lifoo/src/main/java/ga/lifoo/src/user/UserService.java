package ga.lifoo.src.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.user.models.*;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

import static ga.lifoo.util.RandomNickname.makeNickName;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * 회원가입
     */
    @Transactional
    public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {

        //중복 snsId를 가진 유저가 있는지 확인
        Optional<UserInfo> findSnsId = userRepository.findBySnsIdAndIsDeleted(postUserReq.getSnsId(),"N");
        if(findSnsId.isPresent()) {
            throw new BaseException(BaseResponseStatus.ALREADY_SNSID_ERROR);
        }
        //중복 nickname인지 확인
        Optional<UserInfo> findNickname = userRepository.findByNicknameAndIsDeleted(postUserReq.getNickname(),"N");
        if(findNickname.isPresent()) {
            throw new BaseException(BaseResponseStatus.ALREADY_NICKNAME_ERROR);
        }
        //loginType이 KAKAO,APPLE 중 하나인지 확인
        LoginType loginType;
        try{
            loginType = LoginType.valueOf(postUserReq.getLoginType()) ;
        } catch (Exception e){
            throw new BaseException(BaseResponseStatus.INVALID_TYPE);
        }

        //가입
        UserInfo userInfo = new UserInfo(loginType, postUserReq.getSnsId(), postUserReq.getNickname());
        UserInfo saveUserInfo = userRepository.save(userInfo);
        Long id = saveUserInfo.getUserIdx();

        //jwt생성
        String jwt=jwtService.createJwt(id);
        return new PostUserRes(jwt,id);
    }

    @Transactional
    public void patchUser(Long userIdx, PatchUserReq patchUserReq) throws BaseException{

        //존재하지 않는 회원인지 확인
        Optional<UserInfo> findUser = userRepository.findByUserIdxAndIsDeleted(userIdx,"N");
        if(!findUser.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }

        //TODO : 이미 존재하는 닉네임인지 확인

        //회원 정보 업데이트
        findUser.get().setNickname(patchUserReq.getNickname());

    }

    @Transactional
    public void deleteUser(Long userIdx) throws BaseException {

        //존재하지 않는 회원인지 확인
        Optional<UserInfo> findUser = userRepository.findByUserIdxAndIsDeleted(userIdx,"N");
        if(!findUser.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }

        //회원 삭제
        findUser.get().setIsDeleted("Y");

    }

    @Transactional
    public GetUserRes getUser(Long userIdx) throws BaseException {

        //존재하지 않는 회원인지 확인
        Optional<UserInfo> findUser = userRepository.findByUserIdxAndIsDeleted(userIdx,"N");
        if(!findUser.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }

        GetUserRes getUserRes = new GetUserRes(findUser.get().getNickname());

        System.out.println("getUserRes : "+getUserRes);

        return getUserRes;


        //회원 데이터 조회


    }

    @Transactional
    public PostKakaoLoginRes getKakaoUserInfo(PostKakaoLoginReq postKakaoLoginReq) throws BaseException {

        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String accessToken = postKakaoLoginReq.getAccessToken();
        String snsId;
        Optional<UserInfo> findKakaoUser = null;

        //카카오서버와 API 통신, id 값 받아온 후 DB의 snsId값과 비교
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            com.google.gson.JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            snsId = element.getAsJsonObject().get("id").getAsString();

            findKakaoUser = userRepository.findBySnsIdAndIsDeleted(snsId, "N");

            if(!findKakaoUser.isPresent()){
                throw new BaseException(BaseResponseStatus.NEW_USER);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(BaseResponseStatus.INVALID_ACCESS_TOKEN);
        }

        //jwt토큰 생성
        Long userIdx = findKakaoUser.get().getUserIdx();
        String jwt = jwtService.createJwt(userIdx);
        return new PostKakaoLoginRes(userIdx,jwt);
    }

    public GetNicknameRes getNickname() throws BaseException {
        String nickName = null;
        //랜덤 닉네임 만들기
        while(true){
            nickName = makeNickName();
            System.out.println("nickName : "+nickName);

            Optional<UserInfo> findNickname = userRepository.findByNicknameAndIsDeleted(nickName, "N");

            //DB에 없다면
            if(!findNickname.isPresent()){
                break;
            }
        }

        return new GetNicknameRes(nickName);

    }
}
