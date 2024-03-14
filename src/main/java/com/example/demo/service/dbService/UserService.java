package com.example.demo.service.dbService;

import com.example.demo.model.UserInfo;

public interface UserService {
    UserInfo getUserByName(String username);

    void addUser(UserInfo userInfo);
}
