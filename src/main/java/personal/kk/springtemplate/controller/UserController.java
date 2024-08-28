package personal.kk.springtemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import personal.kk.springtemplate.entity.User;
import personal.kk.springtemplate.service.UserService;
import personal.kk.springtemplate.utils.Result;
import personal.kk.springtemplate.dto.LoginDto;

import java.util.List;

/**
 * @author kk
 * @description 用户控制层
 * @date 2024-8-28 08:45:09
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result<Object> Login(@RequestBody LoginDto logindto){
        return Result.ok().setCode(200).setMsg("登录成功").setShowmsg(true).setData(userService.Login(logindto));
    }

    @GetMapping("/getall")
    @ResponseBody
    public Result<List<User>> GetAll(){
        List<User> list  = userService.list();
        for(User user:list){
            System.out.println(user.toString());
        }
        return Result.ok(userService.list());
    }

}
