package personal.kk.springtemplate.execption;

import lombok.Data;

/**
 * @author kk
 * @description 自定义运行时错误
 * @date 2024-5-20 08:23:25
 */
@Data
public class MyRuntimeException extends RuntimeException{
    private String msg;
    private int code;

    public MyRuntimeException(int code,String msg){
        this.msg =msg;
        this.code = code;
    }
    public MyRuntimeException(String msg){
        this.msg = msg;
    }
}
