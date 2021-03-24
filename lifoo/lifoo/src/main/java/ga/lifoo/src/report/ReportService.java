package ga.lifoo.src.report;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.comment.CommentRepository;
import ga.lifoo.src.comment.models.Comment;
import ga.lifoo.src.post.PostRepository;
import ga.lifoo.src.post.models.Post;
import ga.lifoo.src.report.models.PostReportReq;
import ga.lifoo.src.report.models.Report;
import ga.lifoo.src.report.models.ReportStatus;
import ga.lifoo.src.user.UserRepository;
import ga.lifoo.src.user.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public void postReport(PostReportReq postReportReq, Long userIdx) throws BaseException {
        Optional<UserInfo> userInfo = userRepository.findById(userIdx);
        if(!userInfo.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }
        String status = postReportReq.getStatus();

        if(status.equals("POST")){
            //글 신고일 경우
            //없는 게시물일 경우
            Optional<Post> post = postRepository.findById(postReportReq.getTargetIdx());
            if(!post.isPresent()) {
                throw new BaseException(BaseResponseStatus.INVALID_POST);
            }
            //중복신고일 경우
            Optional<Report> findReport = reportRepository.findReport(userIdx, post.get().getPostIdx(), "N", ReportStatus.POST);
            if(!findReport.isPresent()) {
                //첫 신고일 경우
                //게시물의 신고 횟수가 지금 4회였을 경우 게시물 삭제처리
                Long reportNum = reportRepository.findReportNum(post.get().getPostIdx(), "N", ReportStatus.POST);
                if(reportNum >= 4L){
                    post.get().setIsDeleted("Y");
                }
                Report report = new Report(userInfo.get(), ReportStatus.POST, postReportReq.getTargetIdx());
                reportRepository.save(report);
            } else{
                //이미 신고 했을 경우
                throw new BaseException(BaseResponseStatus.ALREADY_REPORT_POST_ERROR);
            }
        } else if(status.equals("COMMENT")){
            //댓글 신고일 경우
            //없는 댓글일 경우
            Optional<Comment> comment = commentRepository.findById(postReportReq.getTargetIdx());
            if(!comment.isPresent()) {
                throw new BaseException(BaseResponseStatus.NOT_EXIST_COMMENT);
            }
            //중복신고일 경우
            Optional<Report> findReport = reportRepository.findReport(userIdx, comment.get().getCommentIdx(), "N",ReportStatus.COMMENT);
            if(!findReport.isPresent()) {
                //첫 신고일 경우
                //댓글의 신고 횟수가 지금 4회였을 경우 게시물 삭제처리
                Long reportNum = reportRepository.findReportNum(comment.get().getCommentIdx(), "N", ReportStatus.COMMENT);
                if(reportNum >= 4L){
                    comment.get().setIsDeleted("Y");
                }
                Report report = new Report(userInfo.get(), ReportStatus.COMMENT, postReportReq.getTargetIdx());
                reportRepository.save(report);
            } else{
                //이미 신고 했을 경우
                throw new BaseException(BaseResponseStatus.ALREADY_REPORT_COMMENT_ERROR);
            }
        } else {
            throw new BaseException(BaseResponseStatus.INVALID_REPORT_TYPE);
        }
    }
}
