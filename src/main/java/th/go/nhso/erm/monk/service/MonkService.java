package th.go.nhso.erm.monk.service;

import th.go.nhso.erm.monk.controller.entity.MonkTrans;

public interface MonkService {

    public void save(MonkTrans monk);

    MonkTrans findByRefId(String refId) throws Exception;
}
