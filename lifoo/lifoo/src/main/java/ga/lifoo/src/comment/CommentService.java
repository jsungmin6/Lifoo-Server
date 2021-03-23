package ga.lifoo.src.comment;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.alarm.models.Alarm;
import ga.lifoo.src.comment.models.Comment;
import ga.lifoo.src.comment.models.PostCommentReq;
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

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

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

}
