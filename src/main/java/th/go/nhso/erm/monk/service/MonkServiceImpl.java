package th.go.nhso.erm.monk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import th.go.nhso.erm.monk.controller.entity.MonkTrans;
import th.go.nhso.erm.monk.repository.MonkRepository;

import java.util.List;

@Service
public class MonkServiceImpl implements MonkService {

    @Autowired
    MonkRepository monkRepository;

    @Override
    public void save(MonkTrans monk) {
        monkRepository.save(monk);
    }

    @Override
    public MonkTrans findByRefId(String refId) throws Exception {
        List<MonkTrans> m = monkRepository.findByRefId(refId);
        if(CollectionUtils.isEmpty(m)){
            throw new Exception("not found data");
        }
        return m.getFirst();
    }
}