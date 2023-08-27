package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * @Description
 * @Author hliu
 * @Date 2023/8/12 20:40
 * @Version 1.0
 */
@SpringBootTest
public class MybatisPlusWrapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name", "a").between("age", 20, 30).isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);

        users.forEach(System.out::println);
    }

    @Test
    public void test02() {
        var queryWrapper = new QueryWrapper<User>();

        queryWrapper.orderByDesc("age").orderByAsc("uid");

        List<User> users = userMapper.selectList(queryWrapper);

        users.forEach(System.out::println);
    }

    @Test
    public void test03() {
        var queryWrapper = new QueryWrapper<User>();
        queryWrapper.isNull("email");
        int delete = userMapper.delete(queryWrapper);

        System.out.println("result " + delete);

    }

    @Test
    public void test04() {
        var queryWrapper = new QueryWrapper<User>();
        queryWrapper.gt("age", 20).like("user_name", "a").or().isNull("email");
        var user = new User();
        user.setName("simon");
        user.setEmail("hliu714@gatech.edu");
        int update = userMapper.update(user, queryWrapper);
        System.out.println("result " + update);
    }

    @Test
    public void test05() {
        var queryWrapper = new QueryWrapper<User>();
        queryWrapper.like("user_name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        var user = new User();
        user.setName("mike");
        user.setEmail("gg@gmail.com");

        int update = userMapper.update(user, queryWrapper);
        System.out.println(update);
    }

    @Test
    public void test06() {
        var queryWrapper = new QueryWrapper<User>();
        QueryWrapper<User> select = queryWrapper.select("user_name", "age", "email");

        List<Map<String, Object>> maps = userMapper.selectMaps(select);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07() {
        var queryWrapper = new QueryWrapper<User>();
        queryWrapper.inSql("uid", "select uid from t_user where uid <= 100");

        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    public void test08() {
        //
        var updateWrapper = new UpdateWrapper<User>();
        updateWrapper.like("user_name", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("user_name", "xiaohei").set("email", "abc@gmail.com");
        int update = userMapper.update(null, updateWrapper);

        System.out.println("result " + update);
    }

    @Test
    public void test09() {
        var username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("user_name", username);
        }

        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }

        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }

        List<User> users = userMapper.selectList(queryWrapper);

        users.forEach(System.out::println);

    }

    @Test
    public void test_10() {
        var username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.like(StringUtils.isNotBlank(username), "user_name", username)
                .ge(ageBegin != null, "age", ageBegin).le(ageEnd != null, "age", ageEnd);

        List<User> users = userMapper.selectList(queryWrapper);

        users.forEach(System.out::println);
    }

    @Test
    public void test_11() {
        var username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();

        userLambdaQueryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin).le(ageEnd != null, User::getAge, ageEnd);

        List<User> users = userMapper.selectList(userLambdaQueryWrapper);

        users.forEach(System.out::println);
    }

    @Test
    public void test12() {

        var updateWrapper = new LambdaUpdateWrapper<User>();
        updateWrapper.like(User::getName, "a").and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName, "xiaohei").set(User::getEmail, "abc@gmail.com");
        int update = userMapper.update(null, updateWrapper);

        System.out.println("result " + update);
    }
}
