package ga.lifoo.src.post;

import ga.lifoo.src.post.models.PostListDto;
import lombok.RequiredArgsConstructor;
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
public class PostOrderRepository {

    private final EntityManager em;

    public List<PostListDto> findPostBasicList(Long size,Long page) {
        Long offset = page * size;
        String sql =
                "SELECT p.post_idx, p.post_title, IFNULL(c.cnt,0), pi.post_url,p.created_at " +
                        "FROM post p " +
                        "JOIN post_img pi " +
                        "ON p.post_idx = pi.post_idx " +
                        "LEFT JOIN " +
                        "        (SELECT COUNT(*) AS cnt, post_idx " +
                        "        FROM user_imoge " +
                        "        WHERE is_clicked = 'Y' " +
                        "        GROUP BY post_idx) AS c " +
                        "ON c.post_idx = p.post_idx " +
                        "WHERE p.is_deleted = 'N' " +
                        "ORDER BY p.created_at DESC " +
                        "LIMIT :size OFFSET :offset";

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter("size", size)
                .setParameter("offset", offset);

        List<Object[]> resultList = nativeQuery.getResultList();

        List<PostListDto> result = new ArrayList<>();

        for (Object[] objects : resultList) {
            BigInteger post_idx = (BigInteger) objects[0];
            String post_title = (String) objects[1];
            BigInteger cnt = (BigInteger) objects[2];
            String post_url = (String) objects[3];
            Timestamp created_at = (Timestamp) objects[4];

            PostListDto postListDto = new PostListDto(post_idx, post_title, cnt, post_url, created_at);
            result.add(postListDto);
        }

        return result;
    }

    public List<PostListDto> findPostRankList(Long size, Long page) {
        Long offset = page * size;
        String sql =
                "SELECT p.post_idx, p.post_title, IFNULL(c.cnt,0), pi.post_url,p.created_at " +
                        "FROM post p " +
                        "JOIN post_img pi " +
                        "ON p.post_idx = pi.post_idx " +
                        "LEFT JOIN " +
                        "        (SELECT COUNT(*) AS cnt, post_idx " +
                        "        FROM user_imoge " +
                        "        WHERE is_clicked = 'Y' " +
                        "        GROUP BY post_idx) AS c " +
                        "ON c.post_idx = p.post_idx " +
                        "WHERE p.is_deleted = 'N' AND c.cnt > 19 " +
                        "ORDER BY p.created_at DESC " +
                        "LIMIT :size OFFSET :offset";

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter("size", size)
                .setParameter("offset", offset);

        List<Object[]> resultList = nativeQuery.getResultList();

        List<PostListDto> result = new ArrayList<>();

        for (Object[] objects : resultList) {
            BigInteger post_idx = (BigInteger) objects[0];
            String post_title = (String) objects[1];
            BigInteger cnt = (BigInteger) objects[2];
            String post_url = (String) objects[3];
            Timestamp created_at = (Timestamp) objects[4];

            PostListDto postListDto = new PostListDto(post_idx, post_title, cnt, post_url, created_at);
            result.add(postListDto);
        }

        return result;
    }

    public List<PostListDto> findPostUserList(Long size, Long page, Long userIdx) {
        Long offset = page * size;
        String sql =
                "SELECT p.post_idx, p.post_title, IFNULL(c.cnt,0), pi.post_url,p.created_at " +
                        "FROM post p " +
                        "JOIN post_img pi " +
                        "ON p.post_idx = pi.post_idx " +
                        "LEFT JOIN " +
                        "        (SELECT COUNT(*) AS cnt, post_idx " +
                        "        FROM user_imoge " +
                        "        WHERE is_clicked = 'Y' " +
                        "        GROUP BY post_idx) AS c " +
                        "ON c.post_idx = p.post_idx " +
                        "WHERE p.is_deleted = 'N' AND p.user_idx = :userIdx " +
                        "ORDER BY p.created_at DESC " +
                        "LIMIT :size OFFSET :offset";

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter("size", size)
                .setParameter("userIdx", userIdx)
                .setParameter("offset", offset);

        List<Object[]> resultList = nativeQuery.getResultList();

        List<PostListDto> result = new ArrayList<>();

        for (Object[] objects : resultList) {
            BigInteger post_idx = (BigInteger) objects[0];
            String post_title = (String) objects[1];
            BigInteger cnt = (BigInteger) objects[2];
            String post_url = (String) objects[3];
            Timestamp created_at = (Timestamp) objects[4];

            PostListDto postListDto = new PostListDto(post_idx, post_title, cnt, post_url, created_at);
            result.add(postListDto);
        }

        return result;
    }

    public List<PostListDto> findPostSearchList(Long size, Long page, String keyword) {
        Long offset = page * size;
        String searchWord = "%"+keyword+"%";
        String sql =
                "SELECT p.post_idx, p.post_title, IFNULL(c.cnt,0), pi.post_url,p.created_at " +
                        "FROM post p " +
                        "JOIN post_img pi " +
                        "ON p.post_idx = pi.post_idx " +
                        "LEFT JOIN " +
                        "        (SELECT COUNT(*) AS cnt, post_idx " +
                        "        FROM user_imoge " +
                        "        WHERE is_clicked = 'Y' " +
                        "        GROUP BY post_idx) AS c " +
                        "ON c.post_idx = p.post_idx " +
                        "WHERE p.is_deleted = 'N' AND p.post_title LIKE :keyword " +
                        "ORDER BY p.created_at DESC " +
                        "LIMIT :size OFFSET :offset";

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter("size", size)
                .setParameter("keyword", searchWord)
                .setParameter("offset", offset);

        List<Object[]> resultList = nativeQuery.getResultList();

        List<PostListDto> result = new ArrayList<>();

        for (Object[] objects : resultList) {
            BigInteger post_idx = (BigInteger) objects[0];
            String post_title = (String) objects[1];
            BigInteger cnt = (BigInteger) objects[2];
            String post_url = (String) objects[3];
            Timestamp created_at = (Timestamp) objects[4];

            PostListDto postListDto = new PostListDto(post_idx, post_title, cnt, post_url, created_at);
            result.add(postListDto);
        }

        return result;
    }
}
