package com.qzy.aop;

import com.alibaba.fastjson.JSON;
import com.qzy.annotation.IgnoreLog;
import com.qzy.context.SystemContext;
import com.qzy.enums.StateEnums;
import com.qzy.pojo.SysLog;
import com.qzy.service.SysLogService;
import com.qzy.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


@Aspect
@Component
@Slf4j
public class RequestAspect {

    @Autowired
    private SysLogService sysLogService;


    @Pointcut("execution( * com.qzy.controller..*(..))")
    public void logPointCut() {
    }


    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        ServletRequestAttributes attributes = ( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        // 获取request
        HttpServletRequest request = attributes.getRequest();
        MethodSignature signature = ( MethodSignature ) pjp.getSignature();
        Method method = signature.getMethod();
        boolean ignoreLog = method.isAnnotationPresent(IgnoreLog.class);

        if (!ignoreLog) {
            String uri = request.getRequestURI();
            // 获取请求方式
            String requestMethod = request.getMethod();
            // 获取请求IP
            String ip = StringUtils.getRemoteIp(request);
            log.info("请求地址：{}", uri);
            log.info("请求方式：{}", requestMethod);
            log.info("请求IP：{}", ip);
            // 获取请求的controller


            String controller = signature.getDeclaringTypeName();
            log.info("请求方法：{}.{}", controller, signature.getName());
            // 获取参数
            Object[] args = pjp.getArgs();
            // 参数不为空，且第一个参数不是Request也不是MultipartFile
            boolean logParamFlag = args != null && args.length > 0 && !(args[0] instanceof ServletRequest) && !(args[0] instanceof MultipartFile);
            // 获取线程上下文
            SysLog sysLog = SystemContext.get().getLogger();
            if (sysLog == null) {
                sysLog = new SysLog();
                SystemContext.get().setLogger(sysLog);
            }
            if (logParamFlag) {
                String params = JSON.toJSONString(args[0]);
                log.info("请求参数：{}", params);
                sysLog.setLogParams(params);
            }
            // 记录日志
            sysLog.setLogUrl(uri);
            sysLog.setLogStatus(StateEnums.REQUEST_SUCCESS.getCode());
            sysLog.setLogMethod(requestMethod);
            sysLog.setLogIp(ip);
            sysLog.setLogUa(request.getHeader("user-Agent"));
            sysLog.setLogController(controller);
        }
        // 记录方法执行时间
        long start = System.currentTimeMillis();
        Object ob = pjp.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        log.info("方法执行耗时：{}", time);
        if (!ignoreLog) {
            SysLog sysLog = SystemContext.get().getLogger();
            sysLog.setLogTime(time);
            // 记录返回值
            String result = JSON.toJSONString(ob);
            log.info("返回值：{}", result);
            sysLog.setLogResult(result);
            // 记录日志
            sysLogService.save(sysLog);
            SystemContext.get().remove();
        }
        return ob;
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "throwable")
    public void doException(JoinPoint joinPoint, Throwable throwable) {
        MethodSignature signature = ( MethodSignature ) joinPoint.getSignature();
        Method method = signature.getMethod();
        boolean annotationPresent = method.isAnnotationPresent(IgnoreLog.class);
        if (!annotationPresent) {
            SysLog sysLog = SystemContext.get().getLogger();
            sysLog.setLogStatus(StateEnums.REQUEST_ERROR.getCode());
            sysLog.setLogMessage(throwable.getMessage());
            sysLog.setLogTime(-1L);
            sysLogService.save(sysLog);
            SystemContext.get().remove();
        }
    }

}
