package personal.kk.springtemplate.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author kk
 * @description 用户实体类
 * @date 2024-8-28 08:03:51
 */
@Data
@TableName("user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String name;

    private String Pw;

    private boolean gender;

    private String role;

    @TableField(value= "depaetment_id")
    private String avator;
}
