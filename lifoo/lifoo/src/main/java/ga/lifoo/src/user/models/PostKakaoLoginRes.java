package ga.lifoo.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class PostKakaoLoginRes {

    private final Long userIdx;
    private final String jwt;
}
