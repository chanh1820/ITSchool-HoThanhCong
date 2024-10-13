package com.nmc.itschool.repository;

import com.nmc.itschool.entity.ScoreEntity;
import com.nmc.itschool.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
    @Query(value ="SELECT * FROM score_tbl WHERE random_id=:randomId", nativeQuery = true)
    Optional<ScoreEntity> findByRandomId(String randomId);
}