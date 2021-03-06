package ga.lifoo.src.post;

import ga.lifoo.src.post.models.Post;
import ga.lifoo.src.user.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Optional<Post> findByPostIdxAndIsDeleted(Long postIdx, String isDeleted);

}
