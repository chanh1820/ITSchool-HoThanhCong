package com.nmc.itschool.repository;

import com.nmc.itschool.entity.LessonCollectionParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonCollectionParentRepository extends JpaRepository<LessonCollectionParentEntity, Integer>{

    @Query(value = "SELECT * FROM lesson_collection_parent_tbl WHERE delete_flag = false" , nativeQuery = true)
    Optional<List<LessonCollectionParentEntity>> getAll();

    @Query(value = "SELECT * FROM lesson_collection_parent_tbl WHERE prefix =:prefix AND delete_flag = false" , nativeQuery = true)
    Optional<LessonCollectionParentEntity> getByPrefix(String prefix);
}
