package ga.lifoo.src.imoge;


import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.imoge.models.PostImogeReq;
import ga.lifoo.src.post.PostService;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ImogeController {

    private final ImogeService imogeService;
    private final JwtService jwtService;
    /**
     * 게시물 이모지 등록 API
     * @RequestBody PostImogeReq
     * @ResponseBody Void
     */
    @ResponseBody
    @PostMapping("/imoges")
    public BaseResponse<Void> postPosts(@RequestBody PostImogeReq postImogeReq)
    {
        System.out.println("start : 게시물 이모지 등록 API");

        Long userIdx;
        try {
            userIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        if(postImogeReq.getImogeIdx()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_IMOGE_IDX_ERROR);
        }
        if(postImogeReq.getPostIdx()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_POST_IDX_ERROR);
        }

        try{
            imogeService.createImoges(postImogeReq,userIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }
}
