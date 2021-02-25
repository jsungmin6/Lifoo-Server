package ga.lifoo.src.user.models;

import lombok.Data;

@Data
public class GetUserIdxRes {
    private Long userIdx;

    public GetUserIdxRes(Long userIdx) {
        this.userIdx = userIdx;
    }
}
