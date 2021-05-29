package com.qzy.mapper;

import com.qzy.pojo.SysLog;
import com.qzy.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface SysLogMapper {


    void save(SysLog sysLog);

    List<SysLog> getByPage(Page page);

    Integer getByCount(Page page);

    SysLog get(Long id);

    void delete(Long id);
}
