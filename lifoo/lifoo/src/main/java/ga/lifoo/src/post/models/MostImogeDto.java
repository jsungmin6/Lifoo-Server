package ga.lifoo.src.post.models;

import lombok.Data;

@Data
public class MostImogeDto {
    private Long cnt;
    private Long imogeIdx;

    public MostImogeDto(Long cnt, Long imogeIdx) {
        this.cnt = cnt;
        this.imogeIdx = imogeIdx;
    }
}
