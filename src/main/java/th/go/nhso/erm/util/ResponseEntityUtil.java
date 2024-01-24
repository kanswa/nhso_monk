package th.go.nhso.erm.util;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import th.go.nhso.erm.resp.BasicHttpResponse;
import th.go.nhso.erm.resp.DataListHttpResponse;
import th.go.nhso.erm.resp.DataObjectHttpResponse;
import th.go.nhso.erm.resp.ExceptionHttpResponse;

import java.util.ArrayList;
import java.util.List;

public class ResponseEntityUtil {


    public static ResponseEntity<Object> returnStatusOk(HttpServletRequest req) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(GsonUtil.toJson(new BasicHttpResponse(HttpStatus.OK, null, req.getRequestURI())));
    }

    public static ResponseEntity<Object> returnDataObject(HttpServletRequest req, Object data) {
        if (null != data) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(GsonUtil.toJson(new DataObjectHttpResponse(req.getRequestURI(), data)));
        } else {
            return returnStatusNotFound(req, null);
        }
    }

    public static ResponseEntity<Object> returnDataList(HttpServletRequest req, List dataList) {
        if (CollectionUtils.isNotEmpty(dataList)) {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(GsonUtil.toJson(new DataListHttpResponse(req.getRequestURI(), dataList)));
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(GsonUtil.toJson(new DataListHttpResponse(req.getRequestURI(), new ArrayList<>())));
        }
    }

    public static ResponseEntity<Object> returnStatusErrorManual(HttpServletRequest req, String errCode, String errMsg) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(GsonUtil.toJson(new ExceptionHttpResponse(HttpStatus.BAD_REQUEST, errMsg, errCode, req.getRequestURI(), "")));
    }

    public static ResponseEntity<Object> returnInternalServeError(HttpServletRequest req, String errCode, String errMsg, String errorStacktrace) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(GsonUtil.toJson(new ExceptionHttpResponse(HttpStatus.INTERNAL_SERVER_ERROR, errMsg, errCode, req.getRequestURI(), errorStacktrace)));
    }

    public static ResponseEntity<Object> returnStatusNotFound(HttpServletRequest req, Exception ex) {
        String stacktrace = ExceptionUtils.getStackTrace(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(GsonUtil.toJson(new ExceptionHttpResponse(HttpStatus.NOT_FOUND, ex, null, req.getRequestURI(), stacktrace)));
    }

}
