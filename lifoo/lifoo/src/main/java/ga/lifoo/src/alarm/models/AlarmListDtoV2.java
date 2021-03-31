package ga.lifoo.src.alarm.models;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Data
public class AlarmListDtoV2 {
    private Long postIdx;
    private String postUrl;
    private Long imogeIdx;
    private String alarmText;
    private String createdAt;

    public AlarmListDtoV2(Long postIdx, String postUrl, Long imogeIdx, String alarmText, String createdAt) {
        this.postIdx = postIdx;
        this.postUrl = postUrl;
        this.imogeIdx = imogeIdx;
        this.alarmText = alarmText;
        this.createdAt = createdAt;
    }
}
