package ga.lifoo.src.comment.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CommentList {

    private BigInteger commentIdx;
    private BigInteger commentUserIdx;
    private String commentNickname;
    private String commentBody;
    private BigInteger likeNum;
    private String isLikeClicked;

    public CommentList(BigInteger commentIdx, BigInteger commentUserIdx, String commentNickname, String commentBody, BigInteger likeNum, String isLikeClicked) {
        this.commentIdx = commentIdx;
        this.commentUserIdx = commentUserIdx;
        this.commentNickname = commentNickname;
        this.commentBody = commentBody;
        this.likeNum = likeNum;
        this.isLikeClicked = isLikeClicked;
    }
}
