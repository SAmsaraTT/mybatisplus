package com.atguigu.mybatisplus.pojo;

import com.atguigu.mybatisplus.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

/**
 * @Description
 * @Author hliu
 * @Date 2023/7/23 23:46
 * @Version 1.0
 */
@Data
//@TableName("t_user")
public class User {
    @TableId(value = "uid")
    private Long id;
    @TableField("user_name")
    private String name;
    private Integer age;
    private String email;

    @TableField("sex")
    private SexEnum sexEnum;

    @TableLogic
    private Integer isDeleted;
}
