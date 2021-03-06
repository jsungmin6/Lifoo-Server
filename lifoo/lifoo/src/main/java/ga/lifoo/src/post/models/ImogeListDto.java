package ga.lifoo.src.post.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ImogeListDto {
    private BigInteger imogeIdx;
    private String isImogeClicked;

    public ImogeListDto(BigInteger imogeIdx, String isImogeClicked) {
        this.imogeIdx = imogeIdx;
        this.isImogeClicked = isImogeClicked;
    }
}
