package personal.kk.springtemplate.service.Imp;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import personal.kk.springtemplate.dto.LoginDto;
import personal.kk.springtemplate.entity.User;
import personal.kk.springtemplate.execption.MyRuntimeException;
import personal.kk.springtemplate.mapper.UserMapper;
import personal.kk.springtemplate.security.JwtUtil;
import personal.kk.springtemplate.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kk
 * @description 用户服务实现层
 * @date 2024-8-28 08:23:00
 */
@Service
@DS("db1")
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Map<String, Object> Login(LoginDto logindto) {
        Map<String,Object> map = new HashMap<>();
        if(!StringUtils.hasText(logindto.getUsername())){
            throw new MyRuntimeException("账号不能为空");
        }else{
            User user = getUserByUsername(logindto.getUsername());
            if(user != null){
                String dbpw = user.getPw();
                String name = user.getName();
                String token = JwtUtil.generateToken(name);
                map.put("user",user);
                map.put("token",token);
                if((dbpw == null && user.getPw() == null) || dbpw.equals(logindto.getPw())){
                    return map;
                }else{
                    throw new MyRuntimeException(202,"密码错误");
                }
            }else{
                throw new MyRuntimeException(201,"账号不存在");
            }
        }
    }

    @Override
    public User getUserByUsername(String name) {
        return null;
    }
}
