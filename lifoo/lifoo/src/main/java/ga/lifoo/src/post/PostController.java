package ga.lifoo.src.post;


import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.post.models.PostPostsReq;
import ga.lifoo.src.user.models.PostUserReq;
import ga.lifoo.src.user.models.PostUserRes;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final JwtService jwtService;
    /**
     * 게시물 등록 API
     * @RequestBody PostPostsReq
     * @ResponseBody Void
     */
    @ResponseBody
    @PostMapping("/posts")
    public BaseResponse<Void> postPosts(@RequestBody PostPostsReq postPostsReq)
    {
        System.out.println("start : 게시물 등록 API ");

        Long userIdx;
        try {
            userIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        if(postPostsReq.getPostUrl()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_URL_ERROR);
        }
        if(postPostsReq.getPostTitle()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_TITLE_ERROR);
        }

        try{
            postService.createPosts(postPostsReq,userIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }
}
