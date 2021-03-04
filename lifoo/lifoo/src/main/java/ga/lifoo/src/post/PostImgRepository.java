package ga.lifoo.src.post;

import ga.lifoo.src.post.models.PostImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImgRepository extends JpaRepository<PostImg,Long> {
}
