package com.qzy.shiro;

import com.qzy.enums.UserTypeEnum;
import org.apache.shiro.authc.UsernamePasswordToken;


public class LoginToken extends UsernamePasswordToken {

    /**
     * 用户类型，1代表后台用户，0代表前台用户
     */
    private UserTypeEnum userType;

    public LoginToken(UserTypeEnum userType) {
        this.userType = userType;
    }

    public LoginToken(String username, char[] password, UserTypeEnum userType) {
        super(username, password);
        this.userType = userType;
    }

    public LoginToken(String username, String password, UserTypeEnum userType) {
        super(username, password);
        this.userType = userType;
    }

    public LoginToken(String username, char[] password, String host, UserTypeEnum userType) {
        super(username, password, host);
        this.userType = userType;
    }

    public LoginToken(String username, String password, String host, UserTypeEnum userType) {
        super(username, password, host);
        this.userType = userType;
    }

    public LoginToken(String username, char[] password, boolean rememberMe, UserTypeEnum userType) {
        super(username, password, rememberMe);
        this.userType = userType;
    }

    public LoginToken(String username, String password, boolean rememberMe, UserTypeEnum userType) {
        super(username, password, rememberMe);
        this.userType = userType;
    }

    public LoginToken(String username, char[] password, boolean rememberMe, String host, UserTypeEnum userType) {
        super(username, password, rememberMe, host);
        this.userType = userType;
    }

    public LoginToken(String username, String password, boolean rememberMe, String host, UserTypeEnum userType) {
        super(username, password, rememberMe, host);
        this.userType = userType;
    }

    public UserTypeEnum getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEnum userType) {
        this.userType = userType;
    }
}

