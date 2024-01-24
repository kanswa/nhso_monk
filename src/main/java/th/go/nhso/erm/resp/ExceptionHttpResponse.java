package th.go.nhso.erm.resp;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExceptionHttpResponse extends BasicHttpResponse {
    
    public static String UNDEFIND_ERROR_CODE = "ERRMON000";
    
    private final String errorCode;

    private final String errorStacktrace;
    private List<String> errorFields;
    
    public ExceptionHttpResponse(int status, String message, String errorCode, String path, String errorStacktrace) {
        super(status, message, path);
        this.errorCode = StringUtils.isNotBlank(errorCode)? errorCode : UNDEFIND_ERROR_CODE;
        this.errorStacktrace = StringUtils.isNotBlank(errorStacktrace)? errorStacktrace : UNDEFIND_ERROR_CODE;
    }

    public ExceptionHttpResponse(int status, Exception ex, String errorCode, String path, String errorStacktrace) {
        super();
        this.timestamp = new Date();
        this.status = status;
        this.message = ex.getMessage();
        this.path = path;
        this.errorCode = StringUtils.isNotBlank(errorCode)? errorCode : UNDEFIND_ERROR_CODE;
        this.errorFields = CollectionUtils.isNotEmpty(errorFields) ? errorFields : new ArrayList<>();
        this.errorStacktrace = StringUtils.isNotBlank(errorStacktrace)? errorStacktrace : UNDEFIND_ERROR_CODE;
    }

    public ExceptionHttpResponse(HttpStatus httpStatus, String message, String errorCode, String path, String errorStacktrace) {
        super(httpStatus, message, path);
        this.errorCode = StringUtils.isNotBlank(errorCode)? errorCode : UNDEFIND_ERROR_CODE;
        this.errorStacktrace = StringUtils.isNotBlank(errorStacktrace)? errorStacktrace : UNDEFIND_ERROR_CODE;
    }

    public ExceptionHttpResponse(HttpStatus httpStatus, String message, String errorCode, String path, List<String> errorFields, String errorStacktrace) {
        super(httpStatus, message, path);
        this.errorCode = StringUtils.isNotBlank(errorCode)? errorCode : UNDEFIND_ERROR_CODE;
        this.errorFields = CollectionUtils.isNotEmpty(errorFields) ? errorFields : new ArrayList<>();
        this.errorStacktrace = StringUtils.isNotBlank(errorStacktrace)? errorStacktrace : UNDEFIND_ERROR_CODE;
    }
    
    public ExceptionHttpResponse(HttpStatus httpStatus, Exception ex, String errorCode, String path, String errorStacktrace) {
        super(httpStatus, null != ex ? ex.getMessage() : null, path);
        this.errorCode = StringUtils.isNotBlank(errorCode)? errorCode : UNDEFIND_ERROR_CODE;
        this.errorStacktrace = StringUtils.isNotBlank(errorStacktrace)? errorStacktrace : UNDEFIND_ERROR_CODE;
    }

    public ExceptionHttpResponse(HttpStatus httpStatus, String message, String errorCode, String path) {
        super(httpStatus, message, path);
        this.errorCode = StringUtils.isNotBlank(errorCode)? errorCode : UNDEFIND_ERROR_CODE;
        this.errorStacktrace = StringUtils.isNotBlank(errorCode)? errorCode : UNDEFIND_ERROR_CODE;
    }

}
