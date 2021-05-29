package com.qzy.shiro;

import com.alibaba.fastjson.JSON;
import com.qzy.enums.ResultEnum;
import com.qzy.utils.Result;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class LoginFilter extends UserFilter {


    /**
     * 登录页面重定向，没登陆就去login.html
     */
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(new Result<>(ResultEnum.NOT_LOGIN)));

    }
}
