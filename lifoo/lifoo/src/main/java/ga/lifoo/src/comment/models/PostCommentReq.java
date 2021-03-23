package ga.lifoo.src.comment.models;

import lombok.Data;

@Data
public class PostCommentReq {
    private Long postIdx;
    private String CommentBody;
}
