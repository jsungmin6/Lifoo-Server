package ga.lifoo.src.imoge.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ImogeListDto {
    private BigInteger imogeIdx;
    private BigInteger ImogeNum;

    public ImogeListDto(BigInteger imogeIdx, BigInteger imogeNum) {
        this.imogeIdx = imogeIdx;
        this.ImogeNum = imogeNum;
    }
}
