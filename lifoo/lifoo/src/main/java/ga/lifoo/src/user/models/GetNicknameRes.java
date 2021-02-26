package ga.lifoo.src.user.models;

import lombok.Data;

@Data
public class GetNicknameRes {
    private String nickname;

    public GetNicknameRes(String nickname) {
        this.nickname = nickname;
    }
}
