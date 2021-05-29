package com.qzy.service;

import com.qzy.pojo.SysLog;
import com.qzy.utils.Page;


public interface SysLogService {


    void save(SysLog sysLog);

    Page<SysLog> getByPage(Page<SysLog> page);

    SysLog get(Long id);

    void delete(Long id);

    Integer totalCount(Page page);
}
