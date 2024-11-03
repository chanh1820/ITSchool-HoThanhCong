package com.nmc.itschool.repository;

import com.nmc.itschool.entity.QuickQuizEntity;
import com.nmc.itschool.entity.QuickQuizLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuickQuizLogRepository extends JpaRepository<QuickQuizLogEntity, Long> {
    @Query(value ="SELECT * FROM quick_quiz_log_tbl WHERE random_id =:randomId", nativeQuery = true)
    Optional<List<QuickQuizLogEntity>> findQuickQuizLogs(String randomId);
}