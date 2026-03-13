package com.lx.user_center.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lx.user_center.common.BaseResponse;
import com.lx.user_center.common.ErrorCode;
import com.lx.user_center.common.ResultUtils;
import com.lx.user_center.expection.BusinessExpection;
import com.lx.user_center.model.domain.User;
import com.lx.user_center.model.domain.request.UserLoginRequest;
import com.lx.user_center.model.domain.request.UserRegisterRequest;
import com.lx.user_center.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.lx.user_center.constant.UserConstant.ADMIN_ROLE;
import static com.lx.user_center.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class userController {

    private final UserService userService;

    public userController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
//            返回缺少 参数的错误
//            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
            throw new BusinessExpection(ErrorCode.PARAMS_ERROR,"无注册信息");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessExpection(ErrorCode.PARAMS_ERROR,"注册信息为空");
        }

        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);


    }

    /**
     * 定义规范(request)实现接口
     *
     * @param userLoginRequest
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<User> userlogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest) {
        if (userLoginRequest == null) {
            throw new BusinessExpection(ErrorCode.PARAMS_ERROR,"账号登录信息为空");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessExpection(ErrorCode.PARAMS_ERROR,"注册信息为空");
        }
        System.out.println("login测试成功");
        User result = userService.userLogin(userAccount, userPassword, httpServletRequest);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObject = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObject;
        if (currentUser == null) {
            throw new BusinessExpection(ErrorCode.NO_LOGIN,"当前用户未登录");
        }
        long userID = currentUser.getId();
//        检测用户是否合法
        User user = userService.getById(userID);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }


    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
//        权限判断
        if (isAdmin(request)) {
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(username)) {
            queryWrapper.like("username", username);
        }

        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(
                user -> userService.getSafetyUser(user)
        ).collect(Collectors.toList());

        return ResultUtils.success(list);


    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        if (isAdmin(request)) {
            return ResultUtils.error(ErrorCode.NO_AUTH);
        }
        if (id <= 0) {
            return ResultUtils.error(ErrorCode.NO_LOGIN);
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }

    private boolean isAdmin(HttpServletRequest request) {
        Object userObject = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObject;
        return user != null && user.getUserRole() == ADMIN_ROLE;
    }


}
