package com.nmc.itschool.repository;

import com.nmc.itschool.entity.SubjectCollectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectCollectionRepository extends JpaRepository<SubjectCollectionEntity, Long>{

    @Query(value = "SELECT * FROM subject_collection_tbl WHERE delete_flag = false" , nativeQuery = true)
    Optional<List<SubjectCollectionEntity>> getAll();

    @Query(value = "SELECT * FROM subject_collection_tbl WHERE prefix =:prefix AND delete_flag = false" , nativeQuery = true)
    Optional<SubjectCollectionEntity> getByPrefix(String prefix);
}
