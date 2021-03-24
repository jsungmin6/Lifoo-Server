package ga.lifoo.src.report;

import ga.lifoo.src.comment.models.UserLike;
import ga.lifoo.src.report.models.Report;
import ga.lifoo.src.report.models.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {

    @Query("select r from Report r " +
            "where r.userInfo.userIdx = :userIdx " +
            "and r.targetIdx = :targetIdx " +
            "and r.status = :status " +
            "and r.isDeleted = :isDeleted")
    Optional<Report> findReport(@Param("userIdx") Long userIdx, @Param("targetIdx") Long targetIdx, @Param("isDeleted") String isDeleted, @Param("status") ReportStatus status);

    @Query("select COALESCE(count(r),0L) from Report r " +
            "where r.targetIdx = :targetIdx " +
            "and r.status = :status " +
            "and r.isDeleted = :isDeleted")
    Long findReportNum(@Param("targetIdx") Long targetIdx, @Param("isDeleted") String isDeleted, @Param("status") ReportStatus status);
}
