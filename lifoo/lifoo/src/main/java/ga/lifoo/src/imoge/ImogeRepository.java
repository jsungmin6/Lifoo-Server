package ga.lifoo.src.imoge;

import ga.lifoo.src.imoge.models.Imoge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImogeRepository extends JpaRepository<Imoge,Long> {
}
