package ga.lifoo.src.comment.models;

import ga.lifoo.src.alarm.models.AlarmListDto;
import lombok.Data;

import java.util.List;

@Data
public class GetCommentRes {
    private List<CommentList> commentLists;

    public GetCommentRes(List<CommentList> commentLists) {
        this.commentLists = commentLists;
    }
}
