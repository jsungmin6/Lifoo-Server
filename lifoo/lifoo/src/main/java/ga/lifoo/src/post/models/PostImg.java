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
@Table(name = "post_img")
public class PostImg extends BaseEntity {

    /**
     * 게시물 이미지 인데스
     */
    @Id
    @Column(name = "post_img_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postImgIdx;

    /**
     * 게시글 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_idx")
    private Post post;

    /**
     * 게시글 url
     */
    @Column(name = "post_url")
    private String postUrl;


}
