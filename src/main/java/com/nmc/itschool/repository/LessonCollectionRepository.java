package com.nmc.itschool.repository;

import com.nmc.itschool.entity.LessonCollectionEntity;
import com.nmc.itschool.entity.LessonCollectionParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonCollectionRepository extends JpaRepository<LessonCollectionEntity, Integer>{

    @Query(value = "SELECT * FROM lesson_collection_tbl WHERE delete_flag = false" , nativeQuery = true)
    Optional<List<LessonCollectionEntity>> getAll();

    @Query(value = "SELECT * FROM lesson_collection_tbl WHERE prefix =:prefix AND delete_flag = false" , nativeQuery = true)
    Optional<LessonCollectionEntity> getByPrefix(String prefix);
}
