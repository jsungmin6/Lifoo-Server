package ga.lifoo.src.post.models;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;

@Data
public class PostListDto {
    private Integer postIdx;
    private String postTitle;
    private BigInteger totalImoge;
    private String postUrl;
    private String createdAt;

    public PostListDto(Integer postIdx, String postTitle, BigInteger totalImoge, String postUrl, String createdAt) {
        this.postIdx = postIdx;
        this.postTitle = postTitle;
        this.totalImoge = totalImoge;
        this.postUrl = postUrl;
        this.createdAt = createdAt;
    }
}
