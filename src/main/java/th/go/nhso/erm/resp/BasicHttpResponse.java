package th.go.nhso.erm.resp;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
public class BasicHttpResponse {

    protected Date timestamp;
    protected int status;
    protected String message;
    protected String path;
    
    public BasicHttpResponse(){
    }

    public BasicHttpResponse(int status, String message, String path) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public BasicHttpResponse(HttpStatus httpStatus, String message, String path) {
        this.timestamp = new Date();
        this.status = httpStatus.value();
        this.message = StringUtils.isNotBlank(message) ? message : httpStatus.getReasonPhrase();
        this.path = path;
    }

}
