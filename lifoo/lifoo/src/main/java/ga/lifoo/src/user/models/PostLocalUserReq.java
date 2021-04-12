package ga.lifoo.src.user.models;

import lombok.Data;

@Data
public class PostLocalUserReq {
    private String nickname;
    private String id;
    private String password;
    private String passwordCheck;
}
