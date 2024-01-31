package th.go.nhso.erm.monk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import th.go.nhso.erm.monk.entity.MonkTrans;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface MonkRepository extends JpaRepository<MonkTrans, Long> {

    List<MonkTrans> findByRefIdAndRecordStatus(String refId, String recordStatus);

    @Query(value = "select m from MonkTrans m where m.recordStatus = :recordStatus",
            nativeQuery = false)
    List<MonkTrans> findMonkByRecordStatus(String recordStatus);

    @Procedure(name = "MonkTrans.querymonk")
    List<Object[]> callQueryMonk(@Param("in_record_status") String inRecordStatus);

    @Procedure(name = "MonkTrans.querymonk")
    List<Object[]> callQueryMonk2(@Param("in_record_status") String inRecordStatus,
                                  @Param("out_count") Integer outCount);

    @Procedure(name = "MonkTrans.querytest")
    Integer callQueryTest(@Param("in_record_status") String in_record_status);

    @Procedure(name = "MonkTrans.querytest2o")
    Map<String, Object> callQueryTest2o(@Param("in_record_status") String in_record_status);
}
