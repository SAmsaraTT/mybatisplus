package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.mapper.ProductMapper;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.atguigu.mybatisplus.pojo.Product;
import com.atguigu.mybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author hliu
 * @Date 2023/8/20 23:45
 * @Version 1.0
 */
@SpringBootTest
public class MybatisPlusPluginsTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void testPage() {
        Page<User> userPage = new Page<>(1, 3);
        userMapper.selectPage(userPage, null);

        System.out.println(userPage.getRecords());
        System.out.println(userPage.getCurrent());
        System.out.println(userPage.getTotal());
        System.out.println(userPage.hasNext());
        System.out.println(userPage.hasPrevious());
    }

    @Test
    public void testPageVo() {
        Page<User> userPage = new Page<>(1, 3);
        userMapper.selectPageVo(userPage, 20);

        System.out.println(userPage.getRecords());
        System.out.println(userPage.getCurrent());
        System.out.println(userPage.getTotal());
        System.out.println(userPage.hasNext());
        System.out.println(userPage.hasPrevious());
    }

    @Test
    public void testProduct01() {
        Product productLi = productMapper.selectById(1);

        System.out.println("Li: " + productLi.getPrice());

        Product productWang = productMapper.selectById(1);
        System.out.println("Wang: " + productWang.getPrice());

        productLi.setPrice(productLi.getPrice() + 50);

        productMapper.updateById(productLi);

        productWang.setPrice(productWang.getPrice() - 30);

        int ret = productMapper.updateById(productWang);

        if (ret == 0) {
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
        }


        Product productBoss = productMapper.selectById(1);

        System.out.println("Boss: " + productBoss.getPrice());
    }
}
