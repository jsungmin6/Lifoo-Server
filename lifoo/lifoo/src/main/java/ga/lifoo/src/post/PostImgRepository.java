package ga.lifoo.src.post;

import ga.lifoo.src.post.models.Post;
import ga.lifoo.src.post.models.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostImgRepository extends JpaRepository<PostImg,Long> {

    @Query("select pi from PostImg pi where pi.post.postIdx = :postIdx")
    Optional<PostImg> findPostImg(@Param("postIdx") Long postIdx);

}
