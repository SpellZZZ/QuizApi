package com.example.demo.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;


public class UserInfoDto implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String name;
    private String email;
    private String password;
    private String roles;

    public UserInfoDto() {
    }

    public UserInfoDto(String name, String email, String password, String roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}