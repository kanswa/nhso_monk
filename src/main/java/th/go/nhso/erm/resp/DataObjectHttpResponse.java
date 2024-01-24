/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.go.nhso.erm.resp;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 *
 * @author kan.s
 */
public class DataObjectHttpResponse extends BasicHttpResponse {

    @Getter
    private final Object data;

    public DataObjectHttpResponse(String path, Object data) {
        super(HttpStatus.OK, null, path);
        this.data = data;
    }
}
