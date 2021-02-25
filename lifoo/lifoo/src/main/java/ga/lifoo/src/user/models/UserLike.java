package ga.lifoo.src.user.models;


import ga.lifoo.config.BaseEntity;
import ga.lifoo.src.comment.models.Comment;
import ga.lifoo.src.imoge.models.Imoge;
import ga.lifoo.src.post.models.Post;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "user_like")
public class UserLike extends BaseEntity {

    /**
     * 유저 좋아요 인덱스
     */
    @Id
    @Column(name = "user_like_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLikeIdx;

    /**
     * 유저 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_idx")
    private UserInfo userInfo;

    /**
     * 댓글 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "comment_idx")
    private Comment comment;

    /**
     * 좋아요 클릭 여부
     */
    @Column(name = "is_clicked", columnDefinition = "char(1) default 'Y'")
    private String isClicked;
}
