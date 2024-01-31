package th.go.nhso.erm.monk.service;

import th.go.nhso.erm.monk.entity.MonkTrans;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MonkService {

    void save(MonkTrans monk) throws Exception;

    MonkTrans findByRefId(String refId) throws Exception;

    void update(MonkTrans editor) throws Exception;

    List<MonkTrans> findAll() throws Exception;

    List<Object[]> queryMonk(String recordStatus) throws Exception;

    Integer queryTest(String recordStatus) throws Exception;

    Map<String, Object> queryTest2o(String recordStatus) throws Exception;
}
