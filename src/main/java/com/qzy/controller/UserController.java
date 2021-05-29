package com.qzy.controller;

import com.qzy.enums.ResultEnum;
import com.qzy.enums.UserTypeEnum;
import com.qzy.exception.BlogException;
import com.qzy.pojo.User;
import com.qzy.pojo.vo.TokenVo;
import com.qzy.service.UserService;
import com.qzy.shiro.LoginToken;
import com.qzy.shiro.LoginUser;
import com.qzy.utils.Result;
import com.qzy.utils.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public Result<?> register(@RequestBody User user) {
        List<String> list = userService.getNames();
        if (list.contains(user.getUsername())) {
            throw new BlogException("注册失败，有相同用户名或者格式错误");
//            return new Result<>("注册失败，有相同用户名或者格式错误",ResultEnum.PARAMS_ERROR);
        }
        userService.save(user);
        return new Result<>("注册成功");
    }

    @PostMapping("/login")
    public Result<TokenVo> login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new LoginToken(user.getUsername(), user.getPassword(), UserTypeEnum.USER);
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(ResultEnum.LOGIN_PARAM_ERROR);
        }
        Serializable token = subject.getSession().getId();
        return new Result<>(new TokenVo(token));
    }

    @GetMapping("/info")
    public Result<LoginUser> info() {
        LoginUser sysUser = ShiroUtils.getLoginUser();
        return new Result<>(sysUser);
    }


    @GetMapping("/logout")
    public Result<?> logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result<>("退出成功！");
    }


    @PutMapping("/updatePwd")
    public Result<?> updatePwd(@RequestBody User user) {
        userService.updatePwd(user);
        return new Result<>("修改成功");
    }


    @PutMapping("/updateInfo")
    public Result<?> updateInfo(@RequestBody User user) {
        userService.update(user);
        // 更新当前登录用户
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(user, loginUser);
        loginUser.setId(user.getUserId());
        ShiroUtils.setUser(loginUser);
        return new Result<>("修改成功！");
    }
}
