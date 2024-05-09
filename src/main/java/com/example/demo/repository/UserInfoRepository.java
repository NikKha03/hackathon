package com.example.demo.repository;

import com.example.demo.models.ForUser.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
