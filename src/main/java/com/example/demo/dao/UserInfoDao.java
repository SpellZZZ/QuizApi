package com.example.demo.dao;


import com.example.demo.model.UserInfo;
import org.springframework.stereotype.Repository;

public interface UserInfoDao {
    UserInfo getUserByName(String username);

    void addUser(UserInfo userInfo);
}
