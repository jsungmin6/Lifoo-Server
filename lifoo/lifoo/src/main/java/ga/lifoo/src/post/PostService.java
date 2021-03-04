package ga.lifoo.src.post;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.post.models.Post;
import ga.lifoo.src.post.models.PostImg;
import ga.lifoo.src.post.models.PostPostsReq;
import ga.lifoo.src.user.UserRepository;
import ga.lifoo.src.user.models.LoginType;
import ga.lifoo.src.user.models.PostUserReq;
import ga.lifoo.src.user.models.PostUserRes;
import ga.lifoo.src.user.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final PostImgRepository postImgRepository;
    private final UserRepository userRepository;

    /**
     * 회원가입
     */
    @Transactional
    public void createPosts(PostPostsReq postPostsReq,Long userIdx) throws BaseException {

        //등록
        Optional<UserInfo> userInfo = userRepository.findById(userIdx);
        if(!userInfo.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }

        Post post = new Post(userInfo.get(), postPostsReq.getPostTitle(), postPostsReq.getPostBody());
        PostImg postImg = new PostImg(post,postPostsReq.getPostUrl());
        postRepository.save(post);
        postImgRepository.save(postImg);
    }


}