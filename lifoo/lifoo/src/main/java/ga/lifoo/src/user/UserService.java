package ga.lifoo.src.user;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.user.models.LoginType;
import ga.lifoo.src.user.models.PostUserReq;
import ga.lifoo.src.user.models.PostUserRes;
import ga.lifoo.src.user.models.UserInfo;
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
        Long id = saveUserInfo.getId();

        //jwt생성
        String jwt=jwtService.createJwt(id);
        return new PostUserRes(jwt,id);
    }
}
