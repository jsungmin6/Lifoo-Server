package ga.lifoo.src.report.models;

import ga.lifoo.config.BaseEntity;
import ga.lifoo.src.user.models.LoginType;
import ga.lifoo.src.user.models.UserInfo;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "report")
public class Report extends BaseEntity {

    /**
     * 신고 인데스
     */
    @Id // PK를 의미하는 어노테이션
    @Column(name = "report_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportIdx;

    /**
     * 유저 인덱스
     */
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_idx")
    private UserInfo userInfo;

    /**
     * 신고 종류
     * POST,COMMENT
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    /**
     * 신고대상
     */
    @Column(name = "target_idx")
    private Long targetIdx;

    public Report(UserInfo userInfo, ReportStatus status, Long targetIdx) {
        this.userInfo = userInfo;
        this.status = status;
        this.targetIdx = targetIdx;
    }

}
