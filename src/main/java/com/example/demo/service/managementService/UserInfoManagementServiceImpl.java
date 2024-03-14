package com.example.demo.service.managementService;


import com.example.demo.auth.UserInfoDetails;
import com.example.demo.model.UserInfo;
import com.example.demo.service.dbService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoManagementServiceImpl implements  UserInfoManagementService, UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService userInfoService;


    /*
       final private PasswordEncoder encoder;
       final private UserInfoService userInfoService;


      /* @Autowired
       public UserInfoManagementServiceImpl(PasswordEncoder encoder, UserInfoService userInfoService){
           this.encoder = encoder;
           this.userInfoService = userInfoService;
       }
       public UserInfoManagementServiceImpl(){

       }
   */
    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userInfoService.addUser(userInfo);
        return "User Added Successfully";
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = Optional.of(userInfoService.getUserByName(username));

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

}
