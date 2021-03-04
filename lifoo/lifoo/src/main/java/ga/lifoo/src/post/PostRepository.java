package ga.lifoo.src.post;

import ga.lifoo.src.post.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {


}
