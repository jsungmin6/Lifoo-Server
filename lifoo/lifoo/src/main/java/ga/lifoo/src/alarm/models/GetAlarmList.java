package ga.lifoo.src.alarm.models;

import lombok.Data;

import java.util.List;

@Data
public class GetAlarmList {
    private List<AlarmListDtoV2> alarmList;

    public GetAlarmList(List<AlarmListDtoV2> alarmList) {
        this.alarmList = alarmList;
    }
}
