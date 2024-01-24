package th.go.nhso.erm.monk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import th.go.nhso.erm.annotation.RunSpringTest;
import th.go.nhso.erm.monk.entity.MonkTrans;

@RunSpringTest
public class MonkServiceTest {

    @Autowired
    MonkService mockService;

    @Test
    void getMonkByRefId(){
        try {
            MonkTrans m = mockService.findByRefId("12345");
            Assertions.assertNotNull(m);
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
