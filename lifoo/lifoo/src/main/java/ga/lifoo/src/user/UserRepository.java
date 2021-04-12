package ga.lifoo.src.user;

import ga.lifoo.src.user.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    //snsId로 user 찾기
    Optional<UserInfo> findBySnsIdAndIsDeleted(String snsId,String isDeleted); //단건 Optional
    //nickname로 user 찾기
    Optional<UserInfo> findByNicknameAndIsDeleted(String nickname,String isDeleted); //단건 Optional
    //userIdx로 user 찾기
    Optional<UserInfo> findByUserIdxAndIsDeleted(Long userIdx, String isDeleted);

    @Query("select ui from UserInfo ui where ui.id = :id and ui.isDeleted = 'N'")
    Optional<UserInfo> findUserId(@Param("id") String id);


}
