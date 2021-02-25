package ga.lifoo.src.user;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.user.models.*;
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
            Long userId = jwtService.getUserId();
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

        Long jwtUserIdx;
        try {
            jwtUserIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
        //PathVariable userIdx와 jwt의 userIdx 비교
        if(!jwtUserIdx.equals(userIdx)){
            return new BaseResponse<>(BaseResponseStatus.NO_AUTHORITY);
        }

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


    /**
     * 회원 삭제 API
     * @RequestBody Void
     * @ResponseBody Void
     */
    @ResponseBody
    @DeleteMapping("/users/{userIdx}")
    public BaseResponse<Void> patchUsers(@PathVariable Long userIdx)
    {
        System.out.println("start : 회원 삭제 API");

        Long jwtUserIdx;
        try {
            jwtUserIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
        //PathVariable userIdx와 jwt의 userIdx 비교
        if(!jwtUserIdx.equals(userIdx)){
            return new BaseResponse<>(BaseResponseStatus.NO_AUTHORITY);
        }

        try{
            userService.deleteUser(userIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 회원 정보 조회 API
     * @RequestBody Void
     * @ResponseBody getUserRes
     */
    @ResponseBody
    @GetMapping("/users/{userIdx}")
    public BaseResponse<GetUserRes> getUsers(@PathVariable Long userIdx)
    {
        System.out.println("start : 회원 조회 API");

        Long jwtUserIdx = null;
        try {
            jwtUserIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
        //PathVariable userIdx와 jwt의 userIdx 비교
        if(!jwtUserIdx.equals(userIdx)){
            return new BaseResponse<>(BaseResponseStatus.NO_AUTHORITY);
        }

        try{
            GetUserRes findUser = userService.getUser(userIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS,findUser);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 회원 인덱스 조회 API
     * @RequestBody Void
     * @ResponseBody getUserIdxRes
     */
    @ResponseBody
    @GetMapping("/users/index")
    public BaseResponse<GetUserIdxRes> getUsers()
    {
        System.out.println("start : 회원 인덱스 조회 API");

        Long jwtUserIdx = null;
        try {
            jwtUserIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        GetUserIdxRes getUserIdxRes = new GetUserIdxRes(jwtUserIdx);

        return new BaseResponse<>(BaseResponseStatus.SUCCESS,getUserIdxRes);

    }
}
