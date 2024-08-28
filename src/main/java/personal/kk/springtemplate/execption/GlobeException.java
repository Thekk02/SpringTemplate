package personal.kk.springtemplate.execption;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import personal.kk.springtemplate.utils.Result;

/**
 * @author kk
 * @description 全局错误类
 * @date 2024-5-20 09:22:07
 */
@RestControllerAdvice
public class GlobeException {

    @ExceptionHandler(Throwable.class)
    public Result<Object> bindException(Throwable exception) {
        exception.printStackTrace();
        return Result.fail().setMsg(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<Object> bindException(Exception exception) {
        exception.printStackTrace();
        return Result.fail().setMsg(exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<Object> bindException(RuntimeException exception) {
        exception.printStackTrace();
        return Result.fail().setMsg(exception.getMessage());
    }


    @ExceptionHandler(MyRuntimeException.class)
    public Result<Object> bindException(MyRuntimeException exception) {
        exception.printStackTrace();
        return Result.fail().setMsg(exception.getMsg()).setCode(exception.getCode());
    }
}
