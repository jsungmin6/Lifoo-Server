package ga.lifoo.src.alarm;

import ga.lifoo.src.alarm.models.Alarm;
import ga.lifoo.src.alarm.models.AlarmListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm,Long> {

    @Query("select new ga.lifoo.src.alarm.models.AlarmListDto(a.post.postIdx, pi.postUrl, a.imoge.imogeIdx, a.alarmText, a.createdAt) " +
            "from Alarm a join PostImg pi on a.post.postIdx = pi.post.postIdx " +
            "where a.isDeleted = 'N' and a.userInfo.userIdx = :userIdx " +
            "order by a.createdAt desc ")
    List<AlarmListDto> getAlarmList(@Param("userIdx") Long userIdx);
}
