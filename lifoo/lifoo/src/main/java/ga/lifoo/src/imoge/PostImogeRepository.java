package ga.lifoo.src.imoge;

import ga.lifoo.src.imoge.models.Imoge;
import ga.lifoo.src.imoge.models.UserImoge;
import ga.lifoo.src.post.models.Post;
import ga.lifoo.src.user.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostImogeRepository extends JpaRepository<UserImoge,Long> {

    @Query("select ui from UserImoge ui where ui.userInfo= :userInfo and ui.post = :post and ui.imoge = :imoge")
    Optional<UserImoge> findUserImogeIdx(@Param("userInfo") UserInfo userInfo, @Param("post") Post post, @Param("imoge") Imoge imoge);

}
