package ga.lifoo.src.comment;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.comment.models.PostCommentReq;
import ga.lifoo.src.imoge.ImogeService;
import ga.lifoo.src.imoge.models.PostImogeReq;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final JwtService jwtService;
    /**
     * 댓글 등록 API
     * @RequestBody PostCommentReq
     * @ResponseBody Void
     */
    @ResponseBody
    @PostMapping("/comments")
    public BaseResponse<Void> postComment(@RequestBody PostCommentReq postCommentReq)
    {
        System.out.println("start : 댓글 등록 API");

        Long userIdx;
        try {
            userIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        if(postCommentReq.getPostIdx()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_POST_IDX_ERROR);
        }
        if(postCommentReq.getCommentBody()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_COMMENT_BODY_ERROR);
        }

        try{
            commentService.createComment(postCommentReq,userIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }
}
