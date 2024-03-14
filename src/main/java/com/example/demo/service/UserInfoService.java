
package com.example.demo.service;




import com.example.demo.auth.UserInfoDetails;
import com.example.demo.auth.UserInfoRepository;
import com.example.demo.model.UserInfo;
import com.example.demo.service.dbService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userDetail = userService.getUserByName(username);

        System.out.println("asd");



        return new org.springframework.security.core.userdetails.User(userDetail.getName(),
                userDetail.getPassword(),
                new ArrayList<>()

        );


        // Converting userDetail to UserDetails
        //return user;
                //userDetail.map(UserInfoDetails::new)
               // .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }


}
