package ga.lifoo.src.post;

import ga.lifoo.src.post.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
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


        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.KOREA);
        datetime.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        for (Object[] objects : resultList) {

            System.out.println("objects[0]"+objects[0].getClass());
            System.out.println("objects[1]"+objects[1].getClass());
            System.out.println("objects[2]"+objects[2].getClass());
            System.out.println("objects[3]"+objects[3].getClass());
            System.out.println("objects[4]"+objects[4].getClass());

            Integer post_idx = (Integer) objects[0];
            String post_title = (String) objects[1];
            BigInteger cnt = (BigInteger) objects[2];
            String post_url = (String) objects[3];
            Timestamp created_at = (Timestamp) objects[4];


            String postDate = datetime.format(created_at);

            System.out.println("created_at : "+created_at);

            PostListDto postListDto = new PostListDto(post_idx, post_title, cnt, post_url, postDate);

            System.out.println("postListDto : " + postListDto);
            result.add(postListDto);
        }

        System.out.println("result : "+result);

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

        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.KOREA);
        datetime.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        for (Object[] objects : resultList) {
            Integer post_idx = (Integer) objects[0];
            String post_title = (String) objects[1];
            BigInteger cnt = (BigInteger) objects[2];
            String post_url = (String) objects[3];
            Timestamp created_at = (Timestamp) objects[4];


            String postDate = datetime.format(created_at);

            PostListDto postListDto = new PostListDto(post_idx, post_title, cnt, post_url, postDate);
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

        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.KOREA);
        datetime.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        for (Object[] objects : resultList) {
            Integer post_idx = (Integer) objects[0];
            String post_title = (String) objects[1];
            BigInteger cnt = (BigInteger) objects[2];
            String post_url = (String) objects[3];
            Timestamp created_at = (Timestamp) objects[4];

            String postDate = datetime.format(created_at);

            PostListDto postListDto = new PostListDto(post_idx, post_title, cnt, post_url, postDate);
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

        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.KOREA);
        datetime.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));


        for (Object[] objects : resultList) {
            Integer post_idx = (Integer) objects[0];
            String post_title = (String) objects[1];
            BigInteger cnt = (BigInteger) objects[2];
            String post_url = (String) objects[3];
            Timestamp created_at = (Timestamp) objects[4];

            String postDate = datetime.format(created_at);

            PostListDto postListDto = new PostListDto(post_idx, post_title, cnt, post_url, postDate);
            result.add(postListDto);
        }

        return result;
    }

    public List<ImogeListDto> findUserClickedImogeList(Long postIdx, Long userIdx) {
        String sql =
                "select i.imoge_idx, IFNULL(ui.is_clicked,'N') as isImogeClicked " +
                        "from imoge as i " +
                        "left join " +
                        "   (select imoge_idx, is_clicked " +
                        "   from user_imoge " +
                        "   where is_clicked = 'Y' and post_idx = :postIdx and user_idx = :userIdx) as ui " +
                        "on i.imoge_idx = ui.imoge_idx";

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter("userIdx", userIdx)
                .setParameter("postIdx", postIdx);

        List<Object[]> resultList = nativeQuery.getResultList();

        List<ImogeListDto> collect = resultList.stream()
                .map(m -> new ImogeListDto((Integer) m[0], (String) m[1]))
                .collect(Collectors.toList());

        return collect;
    }

    public GetPostDetailRes findPostDetail(Long postIdx){
        String sql =
                "select p.post_idx,p.post_title,p.post_body,p.user_idx,u.nickname,p.created_at,pi.post_url from post as p " +
                        "join post_img pi " +
                        "on p.post_idx = pi.post_idx " +
                        "join userInfo u " +
                        "on p.user_idx = u.user_idx " +
                        "where p.post_idx = :postIdx ";

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter("postIdx", postIdx);

        Object[] singleResult = (Object[])nativeQuery.getSingleResult();

        SimpleDateFormat datetime = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.KOREA);
        datetime.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));

        Integer post_idx = (Integer) singleResult[0];
        String post_title = (String) singleResult[1];
        String post_body = (String) singleResult[2];
        Integer user_idx = (Integer) singleResult[3];
        String nickname = (String) singleResult[4];
        Timestamp created_at = (Timestamp) singleResult[5];
        String post_url = (String) singleResult[6];

        String postDate = datetime.format(created_at);

        GetPostDetailRes getPostDetailRes = new GetPostDetailRes(post_idx, post_title, post_body, user_idx, nickname, postDate, post_url);

        return getPostDetailRes;
    }

    public Long gettotalImogeCount(Long postIdx) {
        Long cnt = em.createQuery(
                "select count(ui) " +
                        "from UserImoge ui " +
                        "where ui.isClicked = 'Y' and ui.post.postIdx = :postIdx ", Long.class)
                .setParameter("postIdx", postIdx)
                .getSingleResult();
        return cnt;
    }

    public Long getMostImoge(Long postIdx) {
        Query query = em.createQuery(
                "select count(ui) as cnt, ui.imoge.imogeIdx " +
                        "from UserImoge ui " +
                        "where ui.isClicked = 'Y' and ui.post.postIdx = :postIdx " +
                        "group by ui.imoge.imogeIdx " +
                        "order by cnt desc")
                .setParameter("postIdx", postIdx)
                .setMaxResults(1);

        Object[]  singleResult = (Object[]) query.getSingleResult();

        return (Long)singleResult[1];

    }


    //TODO : JPQL 포기
//    public GetPostDetailRes findPostDetail(Long postIdx) {
//        GetPostDetailRes result = em.createQuery(
//                "select new ga.lifoo.src.post.models.GetPostDetailRes(p.postIdx, p.postTitle,p.postBody,ui.userIdx,ui.nickname,p.createdAt,pi.postUrl) " +
//                        "from PostImg pi " +
//                        "join fetch pi.post p " +
//                        "join fetch p.userInfo ui " +
//                        "where p.postIdx = :postIdx", GetPostDetailRes.class)
//                .setParameter("postIdx", postIdx)
//                .getSingleResult();
//
//        return result;
//    }
}
