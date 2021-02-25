package ga.lifoo.src.user.models;

import lombok.Data;

@Data
public class GetUserRes {
    private String nickname;

    public GetUserRes(String nickname) {
        this.nickname = nickname;
    }
}
