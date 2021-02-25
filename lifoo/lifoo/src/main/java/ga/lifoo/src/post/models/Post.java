package ga.lifoo.src.post.models;

import ga.lifoo.config.BaseEntity;
import ga.lifoo.src.user.models.UserInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "post")
public class Post extends BaseEntity {

    /**
     * 게시물 인데스
     */
    @Id // PK를 의미하는 어노테이션
    @Column(name = "post_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postIdx;

    /**
     * 유저 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_idx")
    private UserInfo userInfo;

    /**
     * 게시글 제목
     */
    @Column(name = "post_title")
    private String postTitle;

    /**
     * 게시글 내용
     */
    @Column(name = "post_body")
    private String postBody;

}
