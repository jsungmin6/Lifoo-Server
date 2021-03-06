package ga.lifoo.src.post.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class GetPostDetailRes {
    private BigInteger postIdx;
    private String postTitle;
    private String postBody;
    private BigInteger hostUserIdx;
    private String hostNickname;
    private Long totalImoge;
    private String postUrl;
    private Timestamp createdAt;
    private Long mostImoge;
    private List<ImogeListDto> imogeList;


    public GetPostDetailRes(BigInteger postIdx, String postTitle, String postBody, BigInteger hostUserIdx, String hostNickname, Timestamp createdAt, String postUrl) {
        this.postIdx = postIdx;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.hostUserIdx = hostUserIdx;
        this.hostNickname = hostNickname;
        this.postUrl = postUrl;
        this.createdAt = createdAt;
    }
}
