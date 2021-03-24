package ga.lifoo.src.report;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.post.PostService;
import ga.lifoo.src.post.models.PostPostsReq;
import ga.lifoo.src.report.models.PostReportReq;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final JwtService jwtService;
    /**
     * 신고 등록 API
     * @RequestBody PostReportReq
     * @ResponseBody Void
     */
    @ResponseBody
    @PostMapping("/reports")
    public BaseResponse<Void> postReport(@RequestBody PostReportReq postReportReq)
    {
        System.out.println("start : 신고 등록 API ");

        Long userIdx;
        try {
            userIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }

        if(postReportReq.getStatus()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_REPORT_TYPE_ERROR);
        }
        if(postReportReq.getTargetIdx()==null){
            return new BaseResponse<>(BaseResponseStatus.EMPTY_TARGET_ERROR);
        }

        try{
            reportService.postReport(postReportReq,userIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }

}
