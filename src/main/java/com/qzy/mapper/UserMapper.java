package com.qzy.mapper;

import com.qzy.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {

    void save(User user);

    void updatePwd(User user);

    void update(User user);

    User getUserByName(String username);

    List<String> getNames();
}
