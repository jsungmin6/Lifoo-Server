package ga.lifoo.src.post.models;

import lombok.Data;

@Data
public class PostListDto {
    private Long postIdx;
    private String postTitle;
    private Long totalImoge;
    private String postUrl;
    private String createdAt;
}
