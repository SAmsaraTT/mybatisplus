package com.atguigu.mybatisplus.mapper;

import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Description
 * @Author hliu
 * @Date 2023/7/23 23:50
 * @Version 1.0
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    Map<String, Object> selectMapById(Long id);

    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
}
