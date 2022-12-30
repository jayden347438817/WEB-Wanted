package com.eureka.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eureka.controller.utils.R;
import com.eureka.dao.UserDao;
import com.eureka.domain.User;
import com.eureka.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Wrapper;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    // 修改密码功能函数
    @Override
    public boolean verifyAndModifyPassword(String username, String newPassword) {
        // 获取条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置查询条件，根据用户名查询
        queryWrapper.eq("username", username);
        // 查询用户
        User user = baseMapper.selectOne(queryWrapper);
        // 判断用户是否存在
        if (user != null) {
            // 设置新密码
            user.setPassword(newPassword);
            // 更新用户信息
            baseMapper.updateById(user);
            return true;
        }
        return false;
    }

    // 修改个人信息功能函数
    @Override
    public boolean modifyData(User user_data){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user_data.getUsername());
        User user = baseMapper.selectOne(queryWrapper);
        if(user != null){
            user.setUsername(user_data.getUsername());
            user.setSchool(user_data.getSchool());
            user.setInstitute(user_data.getInstitute());
            user.setQq_number(user_data.getQq_number());
            user.setPhone(user_data.getPhone());
            user.setStatue(user_data.getStatue());
            user.setBrief(user_data.getBrief());
            baseMapper.updateById(user);
            return true;
        }
        return false;
    }

}
