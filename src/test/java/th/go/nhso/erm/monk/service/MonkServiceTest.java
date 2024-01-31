package th.go.nhso.erm.monk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import th.go.nhso.erm.annotation.RunSpringTest;
import th.go.nhso.erm.monk.entity.MonkTrans;
import th.go.nhso.erm.util.GsonUtil;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunSpringTest
public class MonkServiceTest {

    @Autowired
    MonkService monkService;

    @Test
    void getMonkByRefId() {
        try {
            MonkTrans m = monkService.findByRefId("12345");
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
            monkService.save(m);

            MonkTrans g = monkService.findByRefId(m.getRefId());
            Assertions.assertNotNull(g);
            Assertions.assertEquals(m.getFirstName(), g.getFirstName());
            Assertions.assertEquals(m.getLastName(), g.getLastName());

        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void callProcedureQueryMonk() {
        try {
            List<Object[]> a = monkService.queryMonk("A");
            System.out.println("result : " + GsonUtil.toJson(a));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void callProcedureQueryTest() {
        try {
            Integer a = monkService.queryTest("A");
            System.out.println("result : " + GsonUtil.toJson(a));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }

    @Test
    void callProcedureQueryTest2o() {
        try {
            Map<String, Object> a = monkService.queryTest2o("A");
            System.out.println("result : " + GsonUtil.toJson(a));
        } catch (Exception e) {
            Assertions.fail(e);
        }
    }
}
