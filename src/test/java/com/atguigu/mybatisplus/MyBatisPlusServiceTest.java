package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.pojo.User;
import com.atguigu.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * @Description
 * @Author hliu
 * @Date 2023/7/30 20:55
 * @Version 1.0
 */
@SpringBootTest
public class MyBatisPlusServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetCount() {
        long count = userService.count();

        System.out.println("总记录：" + count);
    }

    @Test
    public void testInsert() {
        var list = new ArrayList<User>();

        for (int i = 1; i < 10; i++) {
            User user = new User();

            user.setName("lht" + i);
            user.setAge(20 + i);
            list.add(user);

        }
        boolean b = userService.saveBatch(list);

        System.out.println(b);
    }


}
