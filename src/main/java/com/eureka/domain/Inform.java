package com.eureka.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


// 使用lombok注解，设置getter、setter方法,toString方法......
@Data
@TableName("tb_inform")
public class Inform {
    private Integer id;
    private String wantedHead;
    private String leader;
    private Integer personCur;
    private Integer personAll;
    private String brief;
    private String school;
    private String institute;
    private String type;
    private Integer member2;
    private Integer member3;
    private Integer member4;
    private Integer member5;
    private Integer member6;
    private Integer member7;
    private Integer member8;
}
