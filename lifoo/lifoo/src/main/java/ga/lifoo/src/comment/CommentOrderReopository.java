package ga.lifoo.src.comment;

import ga.lifoo.src.comment.models.CommentList;
import ga.lifoo.src.imoge.models.ImogeListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CommentOrderReopository {

    private final EntityManager em;

    public List<CommentList> getCommnetList(Long size, Long page, Long postIdx, Long jwtUserIdx) {

        Long offset = page * size;

        String sql =
                "select c.comment_idx, c.user_idx, uI.nickname, c.comment_body, IFNULL(ln.like_num,0) as like_num, IFNULL(ul.is_clicked,'N') as is_clicked from comment as c " +
                        "left join userInfo as uI " +
                        "on uI.user_idx = c.user_idx " +
                        "left join (select user_idx,comment_idx,is_clicked from user_like " +
                        "where user_idx = :userIdx and is_deleted = 'N') as ul " +
                        "on c.comment_idx = ul.comment_idx " +
                        "left join (select comment_idx, count(*) as like_num from user_like " +
                        "where is_deleted = 'N' and is_clicked = 'Y' " +
                        "group by comment_idx) as ln " +
                        "on ln.comment_idx = c.comment_idx " +
                        "where c.post_idx = :postIdx and c.is_deleted = 'N' " +
                        "ORDER BY c.created_at DESC " +
                        "LIMIT :size OFFSET :offset";
                ;

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter("size", size)
                .setParameter("offset", offset)
                .setParameter("postIdx", postIdx)
                .setParameter("userIdx", jwtUserIdx);

        List<Object[]> resultList = nativeQuery.getResultList();

        List<CommentList> collect = resultList.stream()
                .map(m -> new CommentList((BigInteger) m[0],
                                            (BigInteger) m[1],
                                            (String) m[2],
                                            (String) m[3],
                                            (BigInteger) m[4],
                                            (String) m[5]))
                .collect(Collectors.toList());

        return collect;

    }
}
