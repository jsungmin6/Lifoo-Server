package ga.lifoo.src.user;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.user.models.PatchUserReq;
import ga.lifoo.src.user.models.PostUserReq;
import ga.lifoo.src.user.models.PostUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ga.lifoo.util.JwtService;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;

    /**
     * 자동 로그인 API
     */
    @GetMapping("/login/jwt")
    public BaseResponse<Void> getJwt() {
        System.out.println("start : 자동 로그인 api");
        try {
            int userId = jwtService.getUserId();
            //TODO : 존재하지 않는 회원인지 체크
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 회원가입 API
     * @RequestBody PostUserReq
     * @ResponseBody PostUSerRes
     */
    @ResponseBody
    @PostMapping("/users")
    public BaseResponse<PostUserRes> postUsers(@RequestBody PostUserReq postUserReq)
    {
        System.out.println("start : 회원가입 API");

        if(postUserReq.getNickname()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_NICNAME_ERROR);
        }
        if(postUserReq.getLoginType()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_TYPE_ERROR);
        }
        if(postUserReq.getSnsId()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_SNSID_ERROR);
        }

        try{
            PostUserRes postUserRes=userService.createUser(postUserReq);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS,postUserRes);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }

    /**
     * 회원정보 수정 API
     * @RequestBody PatchUserReq
     * @ResponseBody Void
     */
    @ResponseBody
    @PatchMapping("/users/{userIdx}")
    public BaseResponse<Void> patchUsers(@PathVariable Long userIdx, @RequestBody PatchUserReq patchUserReq)
    {
        System.out.println("start : 회원정보 수정 API");

        if(patchUserReq.getNickname()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_NICNAME_ERROR);
        }

        try{
            userService.patchUser(userIdx,patchUserReq);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
