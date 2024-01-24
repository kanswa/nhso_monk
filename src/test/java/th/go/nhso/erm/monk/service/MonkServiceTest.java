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
    void getMonkByRefId() {
        try {
            MonkTrans m = mockService.findByRefId("12345");
            Assertions.assertNotNull(m);
            Assertions.assertEquals("f1", m.getFirstName());
//            Assertions.assertEquals("f2", m.getFirstName());
//            throw new Exception("test error to catch");
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void saveMonk() {
        try {
            MonkTrans m = MonkTrans.builder()
                    .refId("11111")
                    .firstName("f11111")
                    .lastName("l11111")
                    .build()
                    .initData();
            mockService.save(m);

            MonkTrans g = mockService.findByRefId(m.getRefId());
            Assertions.assertNotNull(g);
            Assertions.assertEquals(m.getFirstName(), g.getFirstName());
            Assertions.assertEquals(m.getLastName(), g.getLastName());

        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
