package com.lx.user_center.service;

import javax.annotation.Resource;

import com.lx.user_center.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
//由生成器产生的测试类
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void testAddUser() {
        User user = new User();

        user.setUsername("LX");
        user.setUserAccount("123123");
        user.setAvatarUrl("https://www.codefather.cn/user/1902535112587259905");
        user.setGender(0);
        user.setUserPassword("123");
        user.setPhone("123");
        user.setEmail("123123");


        boolean result = userService.save(user);
        System.out.println("testAddUser测试成功");
        System.out.println(user.getId());
        assertTrue(result);

    }


    @Test
    void userRegister() {
//        测试每一项功能是否能验证
//        密码非法输入的测试
        String userAccount = "lx";
        String userPassword = "";
        String checkPassword = "123456";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        System.out.println("密码非法输入测试");

        userAccount = "l";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        System.out.println("密码校验码非法输入测试");

        userAccount = "lx";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        System.out.println("校验码非法输入测试");

        userAccount = "lx-xl";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        System.out.println("密码非法输入测试");

        checkPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        System.out.println("账号密码非法输入测试");


    }
}