package th.go.nhso.erm.monk.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import th.go.nhso.erm.exception.NotFoundDataException;
import th.go.nhso.erm.monk.entity.MonkTrans;
import th.go.nhso.erm.monk.repository.MonkRepository;

import java.util.List;

@Slf4j
@Service
public class MonkServiceImpl implements MonkService {

    @Autowired
    MonkRepository monkRepository;

    @Override
    public void save(MonkTrans monk) throws Exception {
        try {
            monkRepository.save(monk);
        } catch (Exception e) {
            if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException ex) {
                throw ex;
            }
        }
    }

    @Override
    public MonkTrans findByRefId(String refId) throws Exception {
        List<MonkTrans> m = monkRepository.findByRefIdAndRecordStatus(refId, "A");
        if (CollectionUtils.isEmpty(m)) {
            throw new Exception("not found data");
        }
        return m.getFirst();
    }

    @Override
    public void update(MonkTrans editor) throws Exception {
        List<MonkTrans> m = monkRepository.findByRefIdAndRecordStatus(editor.getRefId(), "A");
        if(CollectionUtils.isEmpty(m)){
            throw new NotFoundDataException();
        }
        MonkTrans monk = m.getFirst();
        monk.setFirstName(editor.getFirstName());
        monk.setLastName(editor.getLastName());
        monkRepository.save(monk.editData());
    }

    @Override
    public List<MonkTrans> findAll() throws Exception {
        return monkRepository.findMonkByRecordStatus("A");
    }
}