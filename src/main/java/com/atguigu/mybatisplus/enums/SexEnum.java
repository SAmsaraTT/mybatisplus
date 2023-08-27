package com.atguigu.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @Description
 * @Author hliu
 * @Date 2023/8/26 22:32
 * @Version 1.0
 */
@Getter
public enum SexEnum {
    MALE(1, "男"), FEMALE(2, "女");

    @EnumValue
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
