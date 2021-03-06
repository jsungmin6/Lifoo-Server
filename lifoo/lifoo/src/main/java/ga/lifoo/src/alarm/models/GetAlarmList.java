package ga.lifoo.src.alarm.models;

import lombok.Data;

import java.util.List;

@Data
public class GetAlarmList {
    private List<AlarmListDto> alarmList;

    public GetAlarmList(List<AlarmListDto> alarmList) {
        this.alarmList = alarmList;
    }
}
