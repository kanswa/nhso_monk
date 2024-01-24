package th.go.nhso.erm.resp;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

public class DataListHttpResponse extends BasicHttpResponse{
    
    @Getter
    private final List data;
    
    public DataListHttpResponse(String path, List dataList) {
        super(HttpStatus.OK , null, path);
        this.data = dataList;
    }
    
}
