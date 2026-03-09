package com.lx.user_center.model.domain.request;

import lombok.Data;

import java.io.Serializable;


/**
 * 封装对象接受请求体参数
 * 用户注册请求体
 *
 * @author lx
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 5326467435845864936L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
