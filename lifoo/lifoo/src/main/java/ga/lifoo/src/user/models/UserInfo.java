package ga.lifoo.src.user.models;

import ga.lifoo.config.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data // from lombok
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "userInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class UserInfo extends BaseEntity {

    /**
     * 유저 ID
     */
    @Id // PK를 의미하는 어노테이션
    @Column(name = "user_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    /**
     * 로그인 타입
     * KAKAO,APPLE,LOCAL
     */
    @Column(name = "login_type")
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    /**
     * snsId
     */
    @Column(name = "sns_id")
    private String snsId;

    /**
     * 닉네임
     */
    @Column(name = "nickname")
    private String nickname;

    public UserInfo(LoginType loginType, String snsId, String nickname) {
        this.loginType = loginType;
        this.snsId = snsId;
        this.nickname = nickname;
    }
}
