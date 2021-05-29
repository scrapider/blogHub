package com.qzy.context;

import com.qzy.pojo.SysLog;
import lombok.Data;

/**
 * 使用ThreadLocal记录线程上下文
 */
@Data
public class SystemContext {

    /**
     * 线程本地内存中的变量
     */
    private static ThreadLocal<SystemContext> threadLocal = new ThreadLocal<>();
    /**
     * 日志实体
     */
    private SysLog logger;

    public static SystemContext get() {
        if (threadLocal.get() == null) {
            SystemContext context = new SystemContext();
            threadLocal.set(context);
        }
        return threadLocal.get();
    }

    public void remove() {
        threadLocal.remove();
    }

}
