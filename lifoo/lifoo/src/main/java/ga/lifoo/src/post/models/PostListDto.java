package ga.lifoo.src.post.models;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
public class PostListDto {
    private BigInteger postIdx;
    private String postTitle;
    private BigInteger totalImoge;
    private String postUrl;
    private Timestamp createdAt;

    public PostListDto(BigInteger postIdx, String postTitle, BigInteger totalImoge, String postUrl, Timestamp createdAt) {
        this.postIdx = postIdx;
        this.postTitle = postTitle;
        this.totalImoge = totalImoge;
        this.postUrl = postUrl;
        this.createdAt = createdAt;
    }
}
