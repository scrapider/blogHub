package com.qzy.service;

import com.qzy.pojo.SysUser;
import com.qzy.pojo.dto.UpdatePwdDto;

public interface SysUserService {
    SysUser getByUsername(String username);

    void update(SysUser sysUser);

    void updatePwd(UpdatePwdDto updatePwdDto);

    SysUser getSysUser();

}
