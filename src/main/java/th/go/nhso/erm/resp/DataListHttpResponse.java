/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.go.nhso.erm.resp;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 *
 * @author kan.s
 */
public class DataListHttpResponse extends BasicHttpResponse{
    
    @Getter
    private final List data;
    
    public DataListHttpResponse(String path, List dataList) {
        super(HttpStatus.OK , null, path);
        this.data = dataList;
    }
    
}
