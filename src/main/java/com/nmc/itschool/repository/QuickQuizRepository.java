package com.nmc.itschool.repository;

import com.nmc.itschool.entity.NoteEntity;
import com.nmc.itschool.entity.QuickQuizEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuickQuizRepository extends JpaRepository<QuickQuizEntity, Long> {
    @Query(value ="SELECT * FROM quick_quiz_tbl WHERE user_name=:userName AND title IS NOT NULL", nativeQuery = true)
    Optional<List<QuickQuizEntity>> findByUserName(String userName);

    @Query(value ="SELECT * FROM quick_quiz_tbl WHERE random_id=:randomId", nativeQuery = true)
    Optional<QuickQuizEntity> findByRandomId(String randomId);
}