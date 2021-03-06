package ga.lifoo.src.imoge;

import ga.lifoo.src.alarm.models.AlarmListDto;
import ga.lifoo.src.imoge.models.Imoge;
import ga.lifoo.src.imoge.models.ImogeListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImogeRepository extends JpaRepository<Imoge,Long> {


}
