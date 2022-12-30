package com.eureka.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eureka.domain.User;


public interface UserService extends IService<User> {
    // 验证并修改密码
    boolean verifyAndModifyPassword(String username, String newPassword);
    boolean modifyData(User user_data);
}
