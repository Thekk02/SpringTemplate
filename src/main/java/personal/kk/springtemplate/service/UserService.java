package personal.kk.springtemplate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import personal.kk.springtemplate.entity.User;
import personal.kk.springtemplate.dto.LoginDto;

import java.util.Map;

/**
 * @author kk
 * @description 用户服务层
 * @date 2024-8-28 08:18:44
 */
public interface UserService extends IService<User> {
    Map<String,Object> Login(LoginDto logindto);


    User getUserByUsername(String name);
}
