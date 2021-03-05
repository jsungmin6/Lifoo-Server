package ga.lifoo.src.post.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostPostsReq {
    private String postUrl;
    private String postTitle;
    private String postBody;
}
