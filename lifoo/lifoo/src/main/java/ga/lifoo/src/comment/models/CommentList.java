package ga.lifoo.src.comment.models;

import lombok.Data;

import java.math.BigInteger;


@Data
public class CommentList {

    private Integer commentIdx;
    private Integer commentUserIdx;
    private String commentNickname;
    private String commentBody;
    private BigInteger likeNum;
    private String isLikeClicked;

    public CommentList(Integer commentIdx, Integer commentUserIdx, String commentNickname, String commentBody, BigInteger likeNum, String isLikeClicked) {
        this.commentIdx = commentIdx;
        this.commentUserIdx = commentUserIdx;
        this.commentNickname = commentNickname;
        this.commentBody = commentBody;
        this.likeNum = likeNum;
        this.isLikeClicked = isLikeClicked;
    }
}
