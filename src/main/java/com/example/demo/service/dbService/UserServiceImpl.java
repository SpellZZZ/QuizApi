package com.example.demo.service.dbService;

import com.example.demo.dao.UserInfoDao;
import com.example.demo.model.UserInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    final private UserInfoDao userInfoDao;


    @Autowired
    UserServiceImpl(UserInfoDao userInfoDao){
        this.userInfoDao = userInfoDao;
    }


    @Override
    @Transactional
    public UserInfo getUserByName(String username) {
        return userInfoDao.getUserByName(username);
    }

    @Override
    @Transactional
    public void addUser(UserInfo userInfo) {
        userInfoDao.addUser(userInfo);
    }
}
