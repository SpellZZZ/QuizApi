
package com.example.demo.service.managementService;





import com.example.demo.model.UserInfo;
import com.example.demo.service.dbService.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserInfoManagementServiceImpl implements UserInfoManagementService, UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo userDetail = userService.getUserByName(username);

        ArrayList<GrantedAuthority> roles = new ArrayList<>();
        GrantedAuthority ga = new SimpleGrantedAuthority(userDetail.getRoles());
        roles.add(ga);

        return new org.springframework.security.core.userdetails.User(userDetail.getName(),
                userDetail.getPassword(),
                roles

        );


    }

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userService.addUser(userInfo);
        return "User Added Successfully";
    }


}
