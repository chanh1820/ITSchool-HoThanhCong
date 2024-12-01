package com.nmc.itschool.repository;

import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long>{
    @Query(value = "SELECT * FROM test_tbl WHERE delete_flag = false ORDER BY test_id DESC LIMIT 15" , nativeQuery = true)
    Optional<List<TestEntity>> getAll(int limit);

    @Query(value = "SELECT * FROM test_tbl WHERE slug=:slug AND delete_flag = false" , nativeQuery = true)
    Optional<TestEntity> findBySlug(String slug);

    @Query(value = "SELECT * FROM test_tbl WHERE collection_parent_prefix=:prefix " +
            "OR collection_prefix=:prefix AND delete_flag = false" , nativeQuery = true)
    Optional<List<TestEntity>> findByPrefix(String prefix);

    @Query(value = "SELECT * FROM test_tbl WHERE delete_flag = false AND is_available = true AND test_collection_uuid =:UUID ORDER BY test_id DESC LIMIT 15" , nativeQuery = true)
    Optional<List<TestEntity>> findByCollectionUUID(String UUID);
}
