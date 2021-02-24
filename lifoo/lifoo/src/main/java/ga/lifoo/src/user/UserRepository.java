package ga.lifoo.src.user;

import ga.lifoo.src.user.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findBySnsId(String snsId); //단건 Optional

    Optional<UserInfo> findByNickname(String nickname); //단건 Optional

}
