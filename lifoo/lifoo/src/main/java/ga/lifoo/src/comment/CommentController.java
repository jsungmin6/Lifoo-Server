package ga.lifoo.src.comment;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.comment.models.GetCommentRes;
import ga.lifoo.src.comment.models.PostCommentReq;
import ga.lifoo.src.comment.models.PutCommentReq;
import ga.lifoo.src.imoge.ImogeService;
import ga.lifoo.src.imoge.models.PostImogeReq;
import ga.lifoo.src.post.models.GetPostsRes;
import ga.lifoo.src.post.models.PostPostsReq;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


    /**
     * 댓글 조회 API
     * @ResponseBody GetPostsRes
     */
    @ResponseBody
    @GetMapping("/posts/{postIdx}/comments")
    public BaseResponse<GetCommentRes> getComments(@PathVariable Long postIdx,
                                                   @RequestParam(value = "size", required = false) Long size,
                                                   @RequestParam(value = "page", required = false) Long page)
    {
        System.out.println("댓글 조회 API ");

        Long jwtUserIdx;
        try {
            jwtUserIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        if(size==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_SIZE_ERROR);
        }
        if(page==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_PAGE_ERROR);
        }
        if(postIdx==null){
            return new BaseResponse<>(BaseResponseStatus.INVALID_POST);
        }

        try{
            GetCommentRes comments = commentService.getComments(size, page, postIdx, jwtUserIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS,comments);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }


    /**
     * 댓글 좋아요 API
     * @ResponseBody Void
     */
    @ResponseBody
    @PostMapping("/comments/{commentIdx}/likes")
    public BaseResponse<Void> postLike(@PathVariable Long commentIdx)
    {
        System.out.println("start : 댓글 좋아요 API");

        Long userIdx;
        try {
            userIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }


        try{
            commentService.postLike(commentIdx,userIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }

    /**
     * 댓글 수정 API
     * @ResponseBody putCommentReq
     */
    @ResponseBody
    @PutMapping("/comments/{commentIdx}")
    public BaseResponse<Void> putComment(@PathVariable Long commentIdx, @RequestBody PutCommentReq putCommentReq)
    {
        System.out.println("댓글 수정 API ");

        Long userIdx;
        try {
            userIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        if(commentIdx==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_COMMENT_IDX_ERROR);
        }
        if(putCommentReq.getCommentBody()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_COMMENT_BODY_ERROR);
        }


        try{
            commentService.putComment(commentIdx,userIdx,putCommentReq);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 댓글 삭제 API
     * @ResponseBody putCommentReq
     */
    @ResponseBody
    @DeleteMapping("/comments/{commentIdx}")
    public BaseResponse<Void> putComment(@PathVariable Long commentIdx)
    {
        System.out.println("댓글 삭제 API");

        Long userIdx;
        try {
            userIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        if(commentIdx==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_COMMENT_IDX_ERROR);
        }


        try{
            commentService.deleteComment(commentIdx,userIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
