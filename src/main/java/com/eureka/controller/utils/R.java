package com.eureka.controller.utils;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

/**
 * @author Eureka
 * @create 2022/8/31 21:13
 */
@Data
public class R {
    private Boolean flag;
    private Object data;

    private String msg;

    public R(){
    };

    public R(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public R(String msg) {
        this.flag = false;
        this.msg = msg;
    }
}
