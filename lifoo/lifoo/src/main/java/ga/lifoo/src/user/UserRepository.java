package ga.lifoo.src.user;

import ga.lifoo.src.user.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findBySnsId(String snsId); //단건 Optional

    Optional<UserInfo> findByNickname(String nickname); //단건 Optional

}
