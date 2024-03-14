package com.example.demo.auth;

import com.example.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface oldUserInfoRepository extends JpaRepository<UserInfo, Integer> {
    UserInfo findByName(String name);
}