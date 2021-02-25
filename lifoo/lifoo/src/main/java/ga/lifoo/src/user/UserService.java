package ga.lifoo.src.user;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.user.models.*;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        Optional<UserInfo> findSnsId = userRepository.findBySnsId(postUserReq.getSnsId());
        if(findSnsId.isPresent()) {
            throw new BaseException(BaseResponseStatus.ALREADY_SNSID_ERROR);
        }
        //중복 nickname인지 확인
        Optional<UserInfo> findNickname = userRepository.findByNickname(postUserReq.getNickname());
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
        Optional<UserInfo> findUser = userRepository.findById(userIdx);
        if(!findUser.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }

        //회원 정보 업데이트
        findUser.get().setNickname(patchUserReq.getNickname());

    }

    @Transactional
    public void deleteUser(Long userIdx) throws BaseException {

        //존재하지 않는 회원인지 확인
        Optional<UserInfo> findUser = userRepository.findById(userIdx);
        if(!findUser.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }

        //회원 삭제
        findUser.get().setIsDeleted("Y");

    }
}
