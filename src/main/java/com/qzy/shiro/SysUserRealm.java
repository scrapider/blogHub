package com.qzy.shiro;

import com.qzy.enums.ResultEnum;
import com.qzy.enums.UserTypeEnum;
import com.qzy.exception.BlogException;
import com.qzy.pojo.SysUser;
import com.qzy.pojo.User;
import com.qzy.service.SysUserService;
import com.qzy.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("sysUserRealm")
public class SysUserRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        LoginToken loginToken = ( LoginToken ) authenticationToken;
        String username = loginToken.getUsername();
        if (loginToken.getUserType() == UserTypeEnum.SYS_USER) {
            SysUser sysUser = sysUserService.getByUsername(username);
            if (sysUser == null) {
                throw new BlogException(ResultEnum.LOGIN_PARAM_ERROR);
            }
            LoginUser loginUser = new LoginUser();
            BeanUtils.copyProperties(sysUser, loginUser);
            loginUser.setId(sysUser.getId());
            return new SimpleAuthenticationInfo(loginUser, sysUser.getPassword(), this.getName());
        } else {
            User user = userService.getByUserName(username);
            if (user == null) {
                throw new BlogException(ResultEnum.LOGIN_PARAM_ERROR);
            }
            LoginUser loginUser = new LoginUser();
            BeanUtils.copyProperties(user, loginUser);
            loginUser.setId(user.getUserId());
            return new SimpleAuthenticationInfo(loginUser, user.getPassword(), this.getName());
        }
    }
}
