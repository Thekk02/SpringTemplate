package personal.kk.springtemplate.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kk
 * @description 用户登录信息传递类
 * @date 2024-8-28 08:20:52
 */
@Data
public class LoginDto implements Serializable {
    private String Username;
    private String Pw;
}
