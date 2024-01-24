package th.go.nhso.erm.monk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import th.go.nhso.erm.monk.entity.MonkTrans;

import java.util.List;

@Repository
public interface MonkRepository extends JpaRepository<MonkTrans, Long> {

    List<MonkTrans> findByRefIdAndRecordStatus(String refId, String recordStatus);

    @Query(value = "select m from MonkTrans m where m.recordStatus = :recordStatus",
            nativeQuery = false)
    List<MonkTrans> findMonkByRecordStatus(String recordStatus);
}
