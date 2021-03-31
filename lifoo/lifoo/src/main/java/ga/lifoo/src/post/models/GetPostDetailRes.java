package ga.lifoo.src.post.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class GetPostDetailRes {
    private Integer postIdx;
    private String postTitle;
    private String postBody;
    private Integer hostUserIdx;
    private String hostNickname;
    private Long totalImoge;
    private String postUrl;
    private String createdAt;
    private Long mostImoge;
    private List<ImogeListDto> imogeList;


    public GetPostDetailRes(Integer postIdx, String postTitle, String postBody, Integer hostUserIdx, String hostNickname, String createdAt, String postUrl) {
        this.postIdx = postIdx;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.hostUserIdx = hostUserIdx;
        this.hostNickname = hostNickname;
        this.postUrl = postUrl;
        this.createdAt = createdAt;
    }
}
