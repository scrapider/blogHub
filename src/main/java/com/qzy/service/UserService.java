package com.qzy.service;

import com.qzy.pojo.User;

import java.util.List;

public interface UserService {
    void save(User user);

    void updatePwd(User user);

    void update(User user);

    User getByUserName(String username);

    List<String> getNames();
}
