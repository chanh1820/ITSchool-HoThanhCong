package com.nmc.itschool.repository;

import com.nmc.itschool.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value ="SELECT * FROM user_tbl WHERE user_name=:userName", nativeQuery = true)
    Optional<UserEntity> findByUserName(String userName);
}