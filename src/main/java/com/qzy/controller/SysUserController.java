package com.qzy.controller;


import com.qzy.enums.ResultEnum;
import com.qzy.enums.UserTypeEnum;
import com.qzy.pojo.SysUser;
import com.qzy.pojo.dto.UpdatePwdDto;
import com.qzy.pojo.vo.TokenVo;
import com.qzy.service.SysUserService;
import com.qzy.shiro.LoginToken;
import com.qzy.shiro.LoginUser;
import com.qzy.utils.Result;
import com.qzy.utils.ShiroUtils;
import com.qzy.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    @PostMapping("/login")
    public Result<TokenVo> login(@RequestBody SysUser sysUser) {
        if (sysUser == null || StringUtils.isBlank(sysUser.getUsername()) || StringUtils.isBlank(sysUser.getPassword())) {
            return new Result<>(ResultEnum.PARAMS_NULL);
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new LoginToken(sysUser.getUsername(), sysUser.getPassword(), UserTypeEnum.SYS_USER);
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
        LoginUser loginUser = ShiroUtils.getLoginUser();
        return new Result<>(loginUser);
    }

    @GetMapping("/logout")
    public Result<String> logout() {
        String token = ShiroUtils.getToken();
//        redisTemplate.delete(token);
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result<>("退出成功");
    }

    @PutMapping("/update")
    public Result<?> update(@RequestBody SysUser sysUser) {
        sysUserService.update(sysUser);
        return new Result<>("修改成功");
    }

    @PutMapping("/updatePwd")
    public Result<?> updatePwd(@RequestBody UpdatePwdDto updatePwdDto) {
        sysUserService.updatePwd(updatePwdDto);
        return new Result<>("修改成功");
    }

    @GetMapping("/getSysUser")
    public Result<SysUser> get() {
        SysUser sysUser = sysUserService.getSysUser();
        return new Result<>(sysUser);
    }
}
