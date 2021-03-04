package ga.lifoo.src.post;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.post.models.*;
import ga.lifoo.src.user.UserRepository;
import ga.lifoo.src.user.models.LoginType;
import ga.lifoo.src.user.models.PostUserReq;
import ga.lifoo.src.user.models.PostUserRes;
import ga.lifoo.src.user.models.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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



    public GetPostsRes getPosts(String type, Long size, Long page, String keyword) throws BaseException {

        //type != SEARCH

        //type = SEARCH

        //쿼리문 통해 게시물 리스트 와 클릭된 이모지수 가져오기.


        List<PostListDto> postListDtos = new ArrayList<>();

        return new GetPostsRes(postListDtos);
    }
}
