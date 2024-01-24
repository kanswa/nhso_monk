package th.go.nhso.erm.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import th.go.nhso.erm.resp.ExceptionHttpResponse;
import th.go.nhso.erm.util.GsonUtil;
import th.go.nhso.erm.util.ResponseEntityUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestHandleException {

    String DEFAULT_ERROR_CODE = "error";

    /** default error on service */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleDefaultException(Exception ex, WebRequest request) {

        return ResponseEntityUtil.returnInternalServeError(((ServletWebRequest) request).getRequest(), DEFAULT_ERROR_CODE, ex.getMessage(), "");
    }

    /** for check data request is valid */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(Exception ex, WebRequest request) {
        if(ex.getCause() instanceof InvalidFormatException e){

            String errMsg = "ข้อมูล " + e.getPathReference() +
                    " ที่ได้รับ " + e.getValue() +
                    " ต้องเป็นประเภท : " + e.getTargetType();
            return ResponseEntityUtil.returnStatusErrorManual(((ServletWebRequest) request).getRequest(), DEFAULT_ERROR_CODE, errMsg);
        }
        return ResponseEntityUtil.returnStatusErrorManual(((ServletWebRequest) request).getRequest(), DEFAULT_ERROR_CODE, ex.getMessage());
    }

    /** for check data request is valid */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (null == ex.getBindingResult()
                || null == ex.getBindingResult().getAllErrors()
                || ex.getBindingResult().getAllErrors().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(GsonUtil.toJson(new ExceptionHttpResponse(
                            HttpStatus.BAD_REQUEST,
                            ex.getMessage(),
                            DEFAULT_ERROR_CODE,
                            ((ServletWebRequest) request).getRequest().getRequestURI(), "")));
        }

        List el = ex.getBindingResult().getAllErrors();
        List<Map<String, String>> mapList = new ArrayList<>();
        BeanUtilsBean b = new BeanUtilsBean();
        el.forEach(obj -> {
            try {
                mapList.add(b.describe(obj));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex1) {
                log.error("error convert message", ex1);
            }
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(GsonUtil.toJson(new ExceptionHttpResponse(
                        HttpStatus.BAD_REQUEST,
                        mapList
                                .stream()
                                .map(m -> StringUtils.isNotBlank(m.get("code"))
                                        && (((String) m.get("code")).contains("AssertTrue")
                                        || ((String) m.get("code")).contains("AssertFalse"))
                                        ? getLabelMessage(m.get("defaultMessage"))
                                        : getLabelMessageSeqFix(m.get("field"), m.get("defaultMessage")))
                                .reduce("", (s, e) -> s + (StringUtils.isNotBlank(s) ? ", " : "") + e),
                        DEFAULT_ERROR_CODE,
                        ((ServletWebRequest) request).getRequest().getRequestURI(),
                        mapList
                                .stream()
                                .map(m -> getFieldNameValid(m.get("field"))).collect(Collectors.toList()), ""
                )));
    }

    @Autowired
    private MessageSource messageSource;

    private String getLabelMessage(String msg) {
        return getLabelMessage(msg, null);
    }

    private String getLabelMessage(String msg, Object[] objs) {
        String rs = messageSource.getMessage(msg, objs, Locale.ENGLISH);
        return StringUtils.isBlank(rs) ? msg : rs;
    }

    private String getLabelMessageSeqFix(String fieldName, String message) {
        if (StringUtils.isBlank(fieldName) && StringUtils.isNotBlank(message)) {
            return getLabelMessage(message);
        }
        if (StringUtils.isNotBlank(fieldName) && StringUtils.isBlank(message)) {
            return getLabelMessageSeq(fieldName);
        }
        String msg = getLabelMessage(message);
        if ("กรุณาระบุ".equalsIgnoreCase(msg)) {
            return getLabelMessage(message) + "" + getLabelMessageSeq(fieldName);
        }
        return getLabelMessageSeq(fieldName) + "" + getLabelMessage(message);
    }

    private String getLabelMessageSeq(String msg) {
        Pattern p = Pattern.compile("\\[[0-9]{0,3}\\]");
        Matcher m = p.matcher(msg);
        String index = m.find() ? m.group(0) : "";
        return getLabelMessage(p.matcher(msg).replaceAll(""), null) +
                (StringUtils.isNotBlank(index) ? "(รายการลำดับที่ " + (Integer.parseInt((index
                        .replaceAll("\\[", "")
                        .replaceAll("]", ""))) + 1 + ")") : "");
    }

    private String getFieldNameValid(String str) {
        if (StringUtils.isBlank(str)) return "";
        String[] codes = {"Valid", "NotNull"};
        String result = str;
        for(String m : codes){
            result = result.contains(m) ? result.substring(0, result.indexOf(m)) : result;
        }
        return result;
    }
}
