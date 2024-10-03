package com.nmc.itschool.repository;

import com.nmc.itschool.entity.LessonCollectionParentEntity;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Integer>{
    @Query(value = "SELECT * FROM lesson_tbl WHERE delete_flag = false" , nativeQuery = true)
    Optional<List<LessonEntity>> getAll();
}
