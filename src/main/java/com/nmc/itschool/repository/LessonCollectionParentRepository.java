package com.nmc.itschool.repository;

import com.nmc.itschool.entity.LessonCollectionParentEntity;
import com.nmc.itschool.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LessonCollectionParentRepository extends JpaRepository<LessonCollectionParentEntity, Integer>{

    @Query(value = "SELECT * FROM lesson_collection_parent_tbl WHERE delete_flag = false" , nativeQuery = true)
    Optional<List<LessonCollectionParentEntity>> getAll();
}
