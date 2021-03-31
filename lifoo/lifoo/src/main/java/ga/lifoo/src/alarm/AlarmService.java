package ga.lifoo.src.alarm;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.alarm.models.AlarmListDto;
import ga.lifoo.src.alarm.models.AlarmListDtoV2;
import ga.lifoo.src.alarm.models.GetAlarmList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmService {

    private final AlarmRepository alarmRepository;

    @Transactional
    public GetAlarmList getAlarms(Long userIdx) throws BaseException {

        List<AlarmListDto> alarmList = alarmRepository.getAlarmList(userIdx);

        List<AlarmListDtoV2> alarmListDtoV2s = new ArrayList<>();

        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.KOREA);
        datetime.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        for (AlarmListDto a : alarmList) {
            String postDate = datetime.format(a.getCreatedAt());
            AlarmListDtoV2 alarmListDtoV2 = new AlarmListDtoV2(a.getPostIdx(),a.getPostUrl(),a.getImogeIdx(),a.getAlarmText(),postDate);
            alarmListDtoV2s.add(alarmListDtoV2);
        }

        if(alarmList.isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_EXIST_ALARM_LIST);
        }
        GetAlarmList getAlarmList = new GetAlarmList(alarmListDtoV2s);

        return getAlarmList;

    }
}
