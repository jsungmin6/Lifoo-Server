package ga.lifoo.src.comment;

import ga.lifoo.src.comment.models.Comment;
import ga.lifoo.src.comment.models.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLikeRepository extends JpaRepository<UserLike,Long> {

    @Query("select ul from UserLike ul " +
            "where ul.comment.commentIdx = :commentIdx " +
            "and ul.userInfo.userIdx = :userIdx " +
            "and ul.isDeleted = :isDeleted")
    Optional<UserLike> findByUserIdxAndCommentIdxAndIsDeleted(@Param("userIdx") Long userIdx, @Param("commentIdx") Long commentIdx, @Param("isDeleted") String isDeleted);

}
