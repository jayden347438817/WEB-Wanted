package com.eureka.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


// 使用lombok注解，设置getter、setter方法,toString方法......
@Data
@TableName("tb_wanted")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String qq_number;
    private String school;
    private String institute;
    private String statue;
    private String brief;
}
