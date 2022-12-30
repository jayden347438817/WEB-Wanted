package com.eureka.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eureka.controller.utils.R;
import com.eureka.domain.User;
import com.eureka.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Eureka
 * @create 2022/8/31 20:47
 */
//说明这是一个controller，是spring的一个控制器，进行交互。可以进行接收和回复请求，rest是一种比较流行的书写风格，对于同一个资源，请求路径一致，增删改查通过请求方式的不同进行区分。
@RestController
//RequestMapping为一个请求路径。
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    //获取用户名和密码进行登录;
    @GetMapping("/{username}/{password}")
    public String login(@PathVariable String username, @PathVariable String password) {
        // MP的条件对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // select * from table where username = ? and password = ?
        wrapper.eq("username", username);
        wrapper.eq("password", password);
        User user = userService.getOne(wrapper);
        if (user != null) {
            // 响应信息
            return user.getUsername();
        }
        return "fail";
    }
    //
    @PostMapping
    public String register(@RequestBody User user){
        boolean save = userService.save(user);
        if(save){
            return "success";
        }else{
            return "fail";
        }

    }
    @GetMapping("/{username}")
    public String different(@PathVariable String username){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // select * from table where username = ? and password = ?
        wrapper.eq("username", username);
        User user = userService.getOne(wrapper);
        if (user == null) {
            // 响应信息
            return "success";
        }
        return "fail";
    }

    //修改个人资料
    // !!!相同的请求方式且相同的路径参数，也就是注解完全一致是不能重复的，否则会报错
    // 之前写的是post,跟前面的重复,运行不起来的，改成put,而且根据rest也应该是put
    @PutMapping
    public String modify_data(@RequestBody User user_data) {
        boolean save = userService.modifyData(user_data);
        if (save) {
            return "success";
        } else {
            return "fail";
        }
    }

    //修改密码
    @PutMapping("/{username}/{newPassword}")
    public String modify_password(@PathVariable String username, @PathVariable String newPassword) {
        boolean save = userService.verifyAndModifyPassword(username, newPassword);
        if (save) {
            return "success";
        } else {
            return "fail";
        }
    }

    //个人页面获取信息
    @GetMapping("/detail")
    public R getDetail(@RequestParam("username") String username){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // select * from table where username = ? and password = ?
        wrapper.eq("username", username);
        User user = userService.getOne(wrapper);
        if (user != null) {
            // 响应信息
            return new R(true,user);
        }
        else{
            return new R(false,"没有该用户");
        }
    }
}
