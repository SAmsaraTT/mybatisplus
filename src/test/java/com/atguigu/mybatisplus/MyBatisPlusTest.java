package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author hliu
 * @Date 2023/7/23 23:55
 * @Version 1.0
 */
@SpringBootTest
public class MyBatisPlusTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        User user = new User();
        //user.setId(100L);
        user.setName("张三");
        user.setAge(23);
        user.setEmail("hliu714@gatech.edu");

        int insert = userMapper.insert(user);

        System.out.println("result: " + insert);
        System.out.println("id: " + user.getId());
    }

    @Test
    public void testDelete() {
//        int ret = userMapper.deleteById(1683853645950431233L);
//
//        System.out.println("result: " + ret);

//        var map = new HashMap<String, Object>();
//        map.put("name", "张三");
//        map.put("age", 23);
//
//        int i = userMapper.deleteByMap(map);
//
//        System.out.println("result: " + i);
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int i = userMapper.deleteBatchIds(list);

        System.out.println("result: " + i);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("list@atguigu.com");

        int i = userMapper.updateById(user);

        System.out.println("result: " + i);
    }

    @Test
    public void testSelect() {
//        User user = userMapper.selectById(1L);
//
//        System.out.println(user);
//        List<Long> list = Arrays.asList(1L, 2L, 3L);
//        List<User> users = userMapper.selectBatchIds(list);
//
//        users.forEach(System.out::println);

//        var map = new HashMap<String, Object>();
//
//        map.put("name", "Jack");
//        map.put("age", 20);
//
//        List<User> users = userMapper.selectByMap(map);
//
//        users.forEach(System.out::println);

//        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);

        Map<String, Object> stringObjectMap = userMapper.selectMapById(1L);

        System.out.println(stringObjectMap);

    }
}
