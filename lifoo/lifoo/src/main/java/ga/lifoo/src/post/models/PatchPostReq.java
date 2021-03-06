package ga.lifoo.src.post.models;

import lombok.Data;

@Data
public class PatchPostReq {
    private String postUrl;
    private String postTitle;
    private String postBody;
}
