package ga.lifoo.src.alarm.models;


import ga.lifoo.config.BaseEntity;
import ga.lifoo.src.imoge.models.Imoge;
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
@Table(name = "alarm")
public class Alarm extends BaseEntity {

    /**
     * 알람 인덱스
     */
    @Id
    @Column(name = "alarm_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmIdx;

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
     * 이모지 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "imoge_idx")
    private Imoge imoge;

    /**
     * 알람 내용
     */
    @Column(name = "alarm_text")
    private String alarmText;

    public Alarm(UserInfo userInfo, Post post, Imoge imoge, String alarmText) {
        this.userInfo = userInfo;
        this.post = post;
        this.imoge = imoge;
        this.alarmText = alarmText;
    }
}
