package ga.lifoo.src.alarm;


import ga.lifoo.config.BaseException;
import ga.lifoo.config.BaseResponse;
import ga.lifoo.config.BaseResponseStatus;
import ga.lifoo.src.alarm.models.GetAlarmList;
import ga.lifoo.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;
    private final JwtService jwtService;
    /**
     * 알림 조회 API
     * @ResponseBody getAlarmList
     */
    @ResponseBody
    @GetMapping("/alarms")
    public BaseResponse<GetAlarmList> getAlarm()
    {
        System.out.println("start : 알림 조회 API");

        Long userIdx;
        try {
            userIdx = jwtService.getUserId();
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }


        try{
            GetAlarmList getAlarmList = alarmService.getAlarms(userIdx);
            return new BaseResponse<>(BaseResponseStatus.SUCCESS,getAlarmList);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }

    }
}
