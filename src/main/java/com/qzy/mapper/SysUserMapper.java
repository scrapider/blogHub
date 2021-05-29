package com.qzy.mapper;

import com.qzy.pojo.SysUser;
import com.qzy.pojo.dto.UpdatePwdDto;
import org.springframework.stereotype.Component;


@Component
public interface SysUserMapper {


    SysUser getByUsername(String username);

    void update(SysUser sysUser);

    void updatePwd(UpdatePwdDto updatePwdDto);

    SysUser getAllById(Long id);

    SysUser getSysUser();

}
