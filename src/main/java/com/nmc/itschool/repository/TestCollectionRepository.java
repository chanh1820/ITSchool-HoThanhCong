package com.nmc.itschool.repository;

import com.nmc.itschool.entity.TestCollectionEntity;
import com.nmc.itschool.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestCollectionRepository extends JpaRepository<TestCollectionEntity, Long>{
    @Query(value = "SELECT * FROM test_collection_tbl WHERE delete_flag = false ORDER BY test_id DESC LIMIT :limit" , nativeQuery = true)
    Optional<List<TestCollectionEntity>> getAll(int limit);

    @Query(value = "SELECT * FROM test_collection_tbl WHERE slug=:slug AND delete_flag = false" , nativeQuery = true)
    Optional<TestCollectionEntity> findBySlug(String slug);

    @Query(value = "SELECT * FROM test_collection_tbl WHERE collection_parent_prefix=:prefix " +
            "OR collection_prefix=:prefix AND delete_flag = false" , nativeQuery = true)
    Optional<List<TestCollectionEntity>> findByPrefix(String prefix);
}
