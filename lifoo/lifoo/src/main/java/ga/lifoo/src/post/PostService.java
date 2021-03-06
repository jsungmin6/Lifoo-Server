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
    private final PostOrderRepository postOrderRepository;

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


    /**
     * 게시물 조회
     */
    @Transactional
    public GetPostsRes getPosts(String type, Long size, Long page, String keyword, Long userIdx) throws BaseException {

        List<PostListDto> postList = null;
        GetPostsRes getPostsRes = null;

        //TODO : 람다로 튜닝, 네이티브 쿼리 사용 안하고 할 수 있는지
        if(type.equals("BASIC")){
            postList = postOrderRepository.findPostBasicList(size, page);
            getPostsRes = new GetPostsRes(postList);
        } else if(type.equals("RANK")){
            postList = postOrderRepository.findPostRankList(size, page);
            getPostsRes = new GetPostsRes(postList);
        } else if(type.equals("USER")){
            postList = postOrderRepository.findPostUserList(size, page, userIdx);
            getPostsRes = new GetPostsRes(postList);
        } else if(type.equals("SEARCH")){
            postList = postOrderRepository.findPostSearchList(size, page, keyword);
            getPostsRes = new GetPostsRes(postList);
        }

        if(postList.isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_EXIST_POST_LIST);
        }

        return getPostsRes;
    }
}
