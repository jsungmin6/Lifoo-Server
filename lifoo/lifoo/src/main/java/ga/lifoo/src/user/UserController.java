package ga.lifoo.src.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.src.user.models.PostKakaoLoginReq;
import ga.lifoo.src.user.models.PostKakaoLoginRes;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
