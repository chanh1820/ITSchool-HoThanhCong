package com.nmc.itschool.repository;

import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.LessonPDFEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonPDFRepository extends JpaRepository<LessonPDFEntity, Long>{
    @Query(value = "SELECT * FROM lesson_pdf_tbl WHERE delete_flag = false ORDER BY lesson_id DESC LIMIT :limit " , nativeQuery = true)
    Optional<List<LessonPDFEntity>> getAll(int limit);

    @Query(value = "SELECT * FROM lesson_pdf_tbl WHERE slug=:slug AND delete_flag = false" , nativeQuery = true)
    Optional<List<LessonPDFEntity>> findBySlug(String slug);

    @Query(value = "SELECT * FROM lesson_pdf_tbl WHERE collection_parent_prefix=:prefix " +
            "OR collection_prefix=:prefix AND delete_flag = false" , nativeQuery = true)
    Optional<List<LessonPDFEntity>> findByPrefix(String prefix);
}
