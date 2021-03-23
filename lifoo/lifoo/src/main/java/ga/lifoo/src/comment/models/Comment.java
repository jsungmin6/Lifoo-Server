package ga.lifoo.src.comment.models;


import ga.lifoo.config.BaseEntity;
import ga.lifoo.src.post.models.Post;
import ga.lifoo.src.user.models.UserInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity {

    /**
     * 댓글 인덱스
     */
    @Id
    @Column(name = "comment_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentIdx;

    /**
     * 유저 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_idx")
    private UserInfo userInfo;

    /**
     * 게시물 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_idx")
    private Post post;

    /**
     * 댓글 내용
     */
    @Column(name = "comment_body")
    private String commentBody;

    public Comment(UserInfo userInfo, Post post, String commentBody) {
        this.userInfo = userInfo;
        this.post = post;
        this.commentBody = commentBody;
    }

}
