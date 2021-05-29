package com.qzy.service.impl;

import com.qzy.mapper.SysLogMapper;
import com.qzy.pojo.SysLog;
import com.qzy.service.SysLogService;
import com.qzy.utils.IdWorker;
import com.qzy.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    private IdWorker idWorker;

    @Override
    public void save(SysLog sysLog) {
        sysLog.setLogId(idWorker.nextId());
        sysLog.setCreatedBy("admin");
        sysLogMapper.save(sysLog);
    }

    @Override
    public SysLog get(Long id) {
        return sysLogMapper.get(id);
    }

    @Override
    public void delete(Long id) {
        sysLogMapper.delete(id);
    }

    @Override
    public Integer totalCount(Page page) {
        return sysLogMapper.getByCount(page);
    }

    @Override
    public Page<SysLog> getByPage(Page page) {
        List<SysLog> list = sysLogMapper.getByPage(page);
//        Integer totalCount = sysLogMapper.getByCount(page);
//        page.setTotalCount(totalCount);
        page.setList(list);
        return page;
    }
}
