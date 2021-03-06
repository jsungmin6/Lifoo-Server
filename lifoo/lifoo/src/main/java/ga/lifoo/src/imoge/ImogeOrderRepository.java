package ga.lifoo.src.imoge;

import ga.lifoo.src.imoge.models.ImogeListDto;
import ga.lifoo.src.post.models.PostListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ImogeOrderRepository {

    private final EntityManager em;

    public List<ImogeListDto> findAllMyImoge(Long userIdx) {
        String sql =
                "select i.imoge_idx, IFNULL(c.cnt,0) from imoge as i " +
                        "left join (select imoge_idx, count(*) as cnt from user_imoge " +
                        "where is_clicked = 'Y' and post_idx in (select p.post_idx from post as p " +
                        "where p.user_idx = :userIdx) " +
                        "group by imoge_idx) as c " +
                        "on i.imoge_idx = c.imoge_idx";

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter("userIdx", userIdx);

        List<Object[]> resultList = nativeQuery.getResultList();

        List<ImogeListDto> collect = resultList.stream()
                .map(m -> new ImogeListDto((BigInteger) m[0], (BigInteger) m[1]))
                .collect(Collectors.toList());

        return collect;
    }
}
