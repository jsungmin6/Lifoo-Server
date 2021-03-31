package ga.lifoo.src.post.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ImogeListDto {
    private Integer imogeIdx;
    private String isImogeClicked;

    public ImogeListDto(Integer imogeIdx, String isImogeClicked) {
        this.imogeIdx = imogeIdx;
        this.isImogeClicked = isImogeClicked;
    }
}
