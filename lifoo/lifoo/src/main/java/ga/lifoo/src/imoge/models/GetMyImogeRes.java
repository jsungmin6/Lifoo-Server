package ga.lifoo.src.imoge.models;


import lombok.Data;

import java.util.List;

@Data
public class GetMyImogeRes {
    private List<ImogeListDto> imogeList;

    public GetMyImogeRes(List<ImogeListDto> imogeList) {
        this.imogeList = imogeList;
    }
}
