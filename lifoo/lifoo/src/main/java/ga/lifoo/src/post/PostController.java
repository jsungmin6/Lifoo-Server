package ga.lifoo.src.post;


import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.post.models.GetPostsRes;
import ga.lifoo.src.post.models.PostPostsReq;
import ga.lifoo.src.user.models.PostUserReq;
import ga.lifoo.src.user.models.PostUserRes;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


//    /**
//     * 게시물 조회 API
//     * @ResponseBody GetPostsRes
//     */
//    @ResponseBody
//    @PostMapping("/posts")
//    public BaseResponse<GetPostsRes> postPosts(@RequestParam(value = "type") String type,
//                                               @RequestParam(value = "size") Long size,
//                                               @RequestParam(value = "page") Long page,
//                                               @RequestParam(value = "keyword", required = false) String keyword)
//    {
//        System.out.println("게시물 조회 API ");
//
//        Long userIdx;
//        try {
//            userIdx = jwtService.getUserId();
//        } catch (BaseException exception) {
//            return new BaseResponse<>(exception.getStatus());
//        }
//
//        if(type==null){
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_TYPE_ERROR);
//        }
//        if(size==null){
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_SIZE_ERROR);
//        }
//        if(page==null){
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_PAGE_ERROR);
//        }
//        if(type == "SEARCH" && keyword==null){
//            return new BaseResponse<>(BaseResponseStatus.EMPTY_KEYWORD_ERROR);
//        }
//
//        try{
//            GetPostsRes posts = postService.getPosts(type, size, page, keyword);
//            return new BaseResponse<>(BaseResponseStatus.SUCCESS,posts);
//        }catch (BaseException exception){
//            return new BaseResponse<>(exception.getStatus());
//        }
//
//    }
}
