package ga.lifoo.src.imoge;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.imoge.models.Imoge;
import ga.lifoo.src.imoge.models.PostImogeReq;
import ga.lifoo.src.imoge.models.UserImoge;
import ga.lifoo.src.post.PostRepository;
import ga.lifoo.src.post.models.Post;
import ga.lifoo.src.user.UserRepository;
import ga.lifoo.src.user.models.UserInfo;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImogeService {

    private final ImogeRepository imogeRepository;
    private final PostImogeRepository postImogeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createImoges(PostImogeReq postImogeReq, Long userIdx) throws BaseException {
        Optional<Imoge> imoge = imogeRepository.findById(postImogeReq.getImogeIdx());
        Optional<UserInfo> userInfo = userRepository.findById(userIdx);
        Optional<Post> post = postRepository.findById(postImogeReq.getPostIdx());

        if(!imoge.isPresent()) {
            throw new BaseException(BaseResponseStatus.INVALID_IMOGE);
        }
        if(!userInfo.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }
        if(!post.isPresent()) {
            throw new BaseException(BaseResponseStatus.INVALID_POST);
        }

        Optional<UserImoge> findUserImoge = postImogeRepository.findUserImogeIdx(userInfo.get(), post.get(), imoge.get());
        if(!findUserImoge.isPresent()) {
            //처음 클릭일 경우
            UserImoge userImoge = new UserImoge(userInfo.get(), imoge.get(), post.get(),"Y");
            postImogeRepository.save(userImoge);
        } else {
            //이미 클릭이 되어있을 경우
            if(findUserImoge.get().getIsClicked().equals("Y")){
                findUserImoge.get().setIsClicked("N");
            }
            else{
                findUserImoge.get().setIsClicked("Y");
            }
        }
    }
}
