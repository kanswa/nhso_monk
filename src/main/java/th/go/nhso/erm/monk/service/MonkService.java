package th.go.nhso.erm.monk.service;

import th.go.nhso.erm.monk.entity.MonkTrans;

import java.util.List;

public interface MonkService {

    void save(MonkTrans monk) throws Exception;

    MonkTrans findByRefId(String refId) throws Exception;

    void update(MonkTrans editor) throws Exception;

    List<MonkTrans> findAll() throws Exception;
}
