package com.lx.user_center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.user_center.model.domain.User;

import javax.servlet.http.HttpServletRequest;


/**
 * 规范接口
 * 业务逻辑
 *
 * @author LX
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2026-03-08 11:54:25
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册逻辑
     *
     * @param userAccount   用户账号
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 返回新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @param request
     * @return 用户登录的信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     *
     * @param request
     */
    int userLogout(HttpServletRequest request);
}
