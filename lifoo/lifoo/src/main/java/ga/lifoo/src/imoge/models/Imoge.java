package ga.lifoo.src.imoge.models;


import ga.lifoo.config.BaseEntity;
import ga.lifoo.src.post.models.Post;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "imoge")
public class Imoge extends BaseEntity {
    /**
     * 이모지 인덱스
     */
    @Id
    @Column(name = "imoge_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imogeIdx;

    /**
     * 이모지 이름
     */
    @Column(name = "imoge_name")
    private String imogeName;
}
