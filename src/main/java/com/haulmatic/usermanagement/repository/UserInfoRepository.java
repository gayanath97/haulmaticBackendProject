package com.haulmatic.usermanagement.repository;

import com.haulmatic.usermanagement.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
    Optional<UserInfo> findByName(String username);
}
