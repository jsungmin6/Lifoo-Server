package ga.lifoo.src.imoge;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.alarm.AlarmRepository;
import ga.lifoo.src.alarm.models.Alarm;
import ga.lifoo.src.imoge.models.*;
import ga.lifoo.src.post.PostRepository;
import ga.lifoo.src.post.models.Post;
import ga.lifoo.src.user.UserRepository;
import ga.lifoo.src.user.models.UserInfo;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImogeService {

    private final ImogeRepository imogeRepository;
    private final PostImogeRepository postImogeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AlarmRepository alarmRepository;
    private final ImogeOrderRepository imogeOrderRepository;

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

        UserInfo postHost = post.get().getUserInfo();

        Optional<UserImoge> findUserImoge = postImogeRepository.findUserImogeIdx(userInfo.get(), post.get(), imoge.get());
        if(!findUserImoge.isPresent()) {
            //처음 클릭일 경우
            UserImoge userImoge = new UserImoge(userInfo.get(), imoge.get(), post.get(),"Y");
            postImogeRepository.save(userImoge);

            //알림 데이터 생성 유저 게시물 이모지 내용
            String alarmText = userInfo.get().getNickname() + "님이 '" + imoge.get().getImogeName() + "' 리액션을 보냈습니다.";
            Alarm alarm = new Alarm(postHost, post.get(), imoge.get(), alarmText);
            alarmRepository.save(alarm);

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

    public GetMyImogeRes getMyImoge(Long userIdx) throws BaseException {
        Optional<UserInfo> userInfo = userRepository.findById(userIdx);
        if(!userInfo.isPresent()) {
            throw new BaseException(BaseResponseStatus.NOT_EXIST_USER);
        }

        List<ImogeListDto> allMyImoge = imogeOrderRepository.findAllMyImoge(userIdx);
        GetMyImogeRes getMyImogeRes = new GetMyImogeRes(allMyImoge);

        return getMyImogeRes;
    }
}
