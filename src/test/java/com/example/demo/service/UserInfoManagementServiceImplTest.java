package com.example.demo.service;

import com.example.demo.model.UserInfo;
import com.example.demo.service.dbService.UserService;
import com.example.demo.service.managementService.UserInfoManagementService;
import com.example.demo.service.managementService.UserInfoManagementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserInfoManagementServiceImplTest {


    @InjectMocks
    UserInfoManagementServiceImpl userInfoManagementService;

    @Mock
    UserService userService;

    @Mock
    PasswordEncoder encoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void addUserTest(){
        when(encoder.encode(anyString())).thenReturn("123");
        doNothing().when(userService).addUser(any(UserInfo.class));

        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(encoder.encode("asdsadsdgsdg"));
        userInfo.setName("name");

        userService.addUser(userInfo);

        verify(userService, times(1)).addUser(userInfo);

        assertEquals(userInfo.getPassword(), "123");
        assertEquals(userInfo.getName(), "name");



    }


    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        userService.addUser(userInfo);
        return "User Added Successfully";
    }






}
