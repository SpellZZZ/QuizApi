
package com.example.demo.service.managementService;





import com.example.demo.model.UserInfo;
import com.example.demo.service.dbService.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class UserInfoManagementServiceImpl implements UserInfoManagementService, UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userDetail = userService.getUserByName(username);



        return new org.springframework.security.core.userdetails.User(userDetail.getName(),
                userDetail.getPassword(),
                new ArrayList<>()

        );


    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userService.addUser(userInfo);
        return "User Added Successfully";
    }


}
