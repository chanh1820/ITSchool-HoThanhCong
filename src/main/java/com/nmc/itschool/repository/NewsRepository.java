package com.nmc.itschool.repository;

import com.nmc.itschool.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long>{
    @Query(value = "SELECT * FROM news_tbl WHERE delete_flag = false ORDER BY news_id DESC LIMIT :limit " , nativeQuery = true)
    Optional<List<NewsEntity>> getAll(int limit);

    @Query(value = "SELECT * FROM news_tbl WHERE slug=:slug AND delete_flag = false" , nativeQuery = true)
    Optional<List<NewsEntity>> findBySlug(String slug);

    @Query(value = "SELECT * FROM news_tbl WHERE collection_parent_prefix=:prefix " +
            "OR collection_prefix=:prefix AND delete_flag = false" , nativeQuery = true)
    Optional<List<NewsEntity>> findByPrefix(String prefix);
}
