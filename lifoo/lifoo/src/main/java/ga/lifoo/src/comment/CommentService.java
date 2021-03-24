package ga.lifoo.src.comment;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.alarm.models.Alarm;
import ga.lifoo.src.alarm.models.AlarmListDto;
import ga.lifoo.src.alarm.models.GetAlarmList;
import ga.lifoo.src.comment.models.*;
import ga.lifoo.src.imoge.models.Imoge;
import ga.lifoo.src.imoge.models.PostImogeReq;
import ga.lifoo.src.imoge.models.UserImoge;
import ga.lifoo.src.post.PostRepository;
import ga.lifoo.src.post.models.Post;
import ga.lifoo.src.user.UserRepository;
import ga.lifoo.src.user.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UserLikeRepository userLikeRepository;
    private final CommentOrderReopository commentOrderReopository;

    public void createComment(PostCommentReq postCommentReq, Long userIdx) throws BaseException {

        Optional<Post> findPost = postRepository.findById(postCommentReq.getPostIdx());
        Optional<UserInfo> userInfo = userRepository.findById(userIdx);

        if(!findPost.isPresent()) {
            throw new BaseException(BaseResponseStatus.INVALID_POST);
        }
        if(!userInfo.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }

        Comment comment = new Comment(userInfo.get(), findPost.get(), postCommentReq.getCommentBody());

        commentRepository.save(comment);

    }

    public GetCommentRes getComments(Long size, Long page, Long postIdx, Long jwtUserIdx) throws BaseException {

        Optional<Post> findPost = postRepository.findById(postIdx);
        Optional<UserInfo> userInfo = userRepository.findById(jwtUserIdx);

        if(!findPost.isPresent()) {
            throw new BaseException(BaseResponseStatus.INVALID_POST);
        }
        if(!userInfo.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }

        List<CommentList> commentLists = commentOrderReopository.getCommnetList(size,page,postIdx,jwtUserIdx);
        if(commentLists.isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_EXIST_COMMENT_LIST);
        }

        GetCommentRes getCommentRes = new GetCommentRes(commentLists);

        return getCommentRes;
    }

    public void postLike(Long commentIdx, Long userIdx) throws BaseException {

        Optional<UserInfo> userInfo = userRepository.findById(userIdx);
        Optional<Comment> findComment = commentRepository.findById(commentIdx);
        if(!userInfo.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }
        if(!findComment.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_COMMENT);
        }

        Optional<UserLike> findUserLike = userLikeRepository.findByUserIdxAndCommentIdxAndIsDeleted(
                userIdx,
                commentIdx,
                "N");

        if(!findUserLike.isPresent()) {
            //처음 클릭일 경우
            UserLike userLike = new UserLike(userInfo.get(), findComment.get(), "Y");
            userLikeRepository.save(userLike);

        } else {
            //이미 클릭이 되어있을 경우
            if(findUserLike.get().getIsClicked().equals("Y")){
                findUserLike.get().setIsClicked("N");
            }
            else{
                findUserLike.get().setIsClicked("Y");
            }
        }

    }
}
