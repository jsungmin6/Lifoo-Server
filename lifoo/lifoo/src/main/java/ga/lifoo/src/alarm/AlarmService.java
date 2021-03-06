package ga.lifoo.src.alarm;

import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.alarm.models.AlarmListDto;
import ga.lifoo.src.alarm.models.GetAlarmList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlarmService {

    private final AlarmRepository alarmRepository;

    @Transactional
    public GetAlarmList getAlarms(Long userIdx) throws BaseException {

        List<AlarmListDto> alarmList = alarmRepository.getAlarmList(userIdx);
        if(alarmList.isEmpty()){
            throw new BaseException(BaseResponseStatus.NOT_EXIST_ALARM_LIST);
        }
        GetAlarmList getAlarmList = new GetAlarmList(alarmList);

        return getAlarmList;

    }
}
