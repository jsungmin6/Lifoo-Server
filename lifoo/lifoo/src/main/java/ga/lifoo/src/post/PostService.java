package ga.lifoo.src.post;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.imoge.models.Imoge;
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

    /**
     * 게시물 상세 조회
     */
    @Transactional
    public GetPostDetailRes getPostDetail(Long postIdx, Long userIdx) throws BaseException {

        Optional<UserInfo> userInfo = userRepository.findById(userIdx);
        Optional<Post> post = postRepository.findById(postIdx);

        if(!userInfo.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }
        if(!post.isPresent()) {
            throw new BaseException(BaseResponseStatus.INVALID_POST);
        }

        
        Long mostImoge;
        List<ImogeListDto> imogeList;
        imogeList = postOrderRepository.findUserClickedImogeList(postIdx,userIdx); //이모지 리스트 구하기
        GetPostDetailRes postDetail = postOrderRepository.findPostDetail(postIdx); // 게시물 정보 구하기
        Long cnt = postOrderRepository.gettotalImogeCount(postIdx); //받은 총 이모지 개수 구하기.

        if(cnt.equals(0L)){ // 총 이모지 개수가 0이면 가장 많이 눌린 이모지 수가 없다. 100 반환
            mostImoge = 100L;
        } else {
            mostImoge = postOrderRepository.getMostImoge(postIdx);
        }

        postDetail.setImogeList(imogeList);
        postDetail.setMostImoge(mostImoge);
        postDetail.setTotalImoge(cnt);

        return postDetail;
    }
}
