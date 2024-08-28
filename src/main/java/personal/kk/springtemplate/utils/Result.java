package personal.kk.springtemplate.utils;

import personal.kk.springtemplate.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author kk
 * @description 响应结果类
 * @date 2024-7-2 09:38:24
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> implements Serializable {
    private T data;

    private Integer code;

    //操作信息
    private String msg;
    private boolean Showmsg = false;

    public static <T> Result<T> ok(T data){
        return build(data,ResultCode.SUCCESS);
    }
    public static <T> Result<T> ok(){
        return ok(null);
    }

    public static <T> Result<T> fail(T data){return build(data,ResultCode.FAIL).setShowmsg(true);}

    public static Result<Object> fail(){return fail(null);}

    public static <T> Result<T> build(T data, ResultCode resultcode){
        Result result = new Result();
        result.setData(data);
        result.setCode(result.getCode());
        result.setMsg(result.getMsg());
        return result;
    }


}
