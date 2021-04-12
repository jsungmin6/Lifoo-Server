package ga.lifoo.src.user.models;

import lombok.Data;

@Data
public class PostLocalUserRes {
    private String jwt;
    private Long userIdx;

    public PostLocalUserRes(String jwt, Long userIdx) {
        this.jwt = jwt;
        this.userIdx = userIdx;
    }
}
