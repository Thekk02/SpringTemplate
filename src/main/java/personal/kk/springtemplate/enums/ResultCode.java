package personal.kk.springtemplate.enums;

/**
 * @author kk
 * @description 结果值枚举
 * @date 2024-5-15 10:14:54
 */
public enum ResultCode {
    SUCCESS(200,"成功"),FAIL(201,"失败"),TOKEN_ERROR(201,"验证码错误"),LOSE(404,"页面丢失");


    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}
