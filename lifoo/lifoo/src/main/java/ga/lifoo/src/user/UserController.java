package ga.lifoo.src.user;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ga.lifoo.util.JwtService;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final JwtService jwtService;

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    /**
     * 자동 로그인 api
     */

    @GetMapping("/login/jwt")
    public BaseResponse<Void> getJwt() {

        try {
            int userId = jwtService.getUserId();
            //TODO : 존재하지 않는 회원인지 체크
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

    }

}
