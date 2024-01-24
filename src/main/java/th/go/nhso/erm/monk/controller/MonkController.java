package th.go.nhso.erm.monk.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import th.go.nhso.erm.monk.controller.entity.MonkTrans;
import th.go.nhso.erm.monk.controller.model.MonkTransModel;
import th.go.nhso.erm.monk.service.MonkServiceImpl;
import th.go.nhso.erm.util.ResponseEntityUtil;

@Slf4j
@Validated
@RestController
public class MonkController {

    @Autowired
    MonkServiceImpl monkService;

    @GetMapping(value = "/monk/{refId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findMonkByRefId(
            @PathVariable String refId,
            HttpServletRequest req) throws Exception {
        MonkTrans m = monkService.findByRefId(refId);
        return ResponseEntityUtil.returnDataObject(req,
                MonkTransModel.builder()
                        .refId(refId)
                        .firstName(m.getFirstName())
                        .lastName(m.getLastName())
                        .build());
    }

    @PostMapping(value = "/monk", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveMonk(
            @RequestBody @Valid MonkTransModel monk,
            HttpServletRequest req
    ) throws Exception {

        monkService.save(new MonkTrans(monk));

        return ResponseEntityUtil.returnStatusOk(req);
    }
}
