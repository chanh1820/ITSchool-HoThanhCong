package com.nmc.itschool.repository;

import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, Long>{
    @Query(value = "SELECT * FROM lesson_tbl WHERE delete_flag = false ORDER BY lesson_id DESC LIMIT :limit " , nativeQuery = true)
    Optional<List<LessonEntity>> getAll(int limit);

    @Query(value = "SELECT * FROM lesson_tbl WHERE slug=:slug AND delete_flag = false" , nativeQuery = true)
    Optional<List<LessonEntity>> findBySlug(String slug);

}
