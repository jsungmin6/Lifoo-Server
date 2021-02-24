package ga.lifoo.src.user.models;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostKakaoLoginReq {
    private String accessToken;
}
