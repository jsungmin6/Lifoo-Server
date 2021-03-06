package ga.lifoo.src.alarm.models;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class AlarmListDto {
    private Long postIdx;
    private String postUrl;
    private Long imogeIdx;
    private String alarmText;
    private Date createdAt;

    public AlarmListDto(Long postIdx, String postUrl, Long imogeIdx, String alarmText, Date createdAt) {
        this.postIdx = postIdx;
        this.postUrl = postUrl;
        this.imogeIdx = imogeIdx;
        this.alarmText = alarmText;
        this.createdAt = createdAt;
    }
}
