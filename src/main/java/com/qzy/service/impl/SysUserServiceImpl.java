package com.qzy.service.impl;

import com.qzy.exception.BlogException;
import com.qzy.mapper.SysUserMapper;
import com.qzy.pojo.SysUser;
import com.qzy.pojo.dto.UpdatePwdDto;
import com.qzy.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser getByUsername(String username) {
        return sysUserMapper.getByUsername(username);
    }

    @Override
    public void update(SysUser sysUser) {
        sysUserMapper.update(sysUser);
    }

    @Override
    public void updatePwd(UpdatePwdDto updatePwdDto) {
        SysUser sysUser = sysUserMapper.getAllById(updatePwdDto.getId());
        if (!sysUser.getPassword().equals(updatePwdDto.getCurrentPwd())) {
            throw new BlogException("验证密码不正确");
        }
        sysUserMapper.updatePwd(updatePwdDto);
    }

    @Override
    public SysUser getSysUser() {
        return sysUserMapper.getSysUser();

    }
}
