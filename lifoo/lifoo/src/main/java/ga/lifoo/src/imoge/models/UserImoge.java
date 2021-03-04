package ga.lifoo.src.imoge.models;


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
@Table(name = "user_imoge")
public class UserImoge extends BaseEntity {

    /**
     * 유저 이모지 인덱스
     */
    @Id
    @Column(name = "user_imoge_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userImogeIdx;

    /**
     * 유저 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_idx")
    private UserInfo userInfo;

    /**
     * 이모지 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "imoge_idx")
    private Imoge imoge;

    /**
     * 게시글 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_idx")
    private Post post;

    /**
     * 이모지 클릭 여부
     */
    @Column(name = "is_clicked", columnDefinition = "char(1) default 'Y'")
    private String isClicked;

    public UserImoge(UserInfo userInfo, Imoge imoge, Post post) {
        this.userInfo = userInfo;
        this.imoge = imoge;
        this.post = post;
    }

    public UserImoge(UserInfo userInfo, Imoge imoge, Post post, String isClicked) {
        this.userInfo = userInfo;
        this.imoge = imoge;
        this.post = post;
        this.isClicked = isClicked;
    }
}
