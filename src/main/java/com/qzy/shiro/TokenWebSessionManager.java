package com.qzy.shiro;

import com.qzy.utils.StringUtils;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.UUID;


@Configuration
public class TokenWebSessionManager extends DefaultWebSessionManager {

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader("Authorization");
        if (StringUtils.isNotBlank(token)) {
            return token;
        }

        return UUID.randomUUID().toString();
    }
}
