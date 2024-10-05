package com.nmc.itschool.repository;

import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectCollectionParentRepository extends JpaRepository<SubjectCollectionParentEntity, Integer>{

    @Query(value = "SELECT * FROM subject_collection_parent_tbl WHERE delete_flag = false" , nativeQuery = true)
    Optional<List<SubjectCollectionParentEntity>> getAll();

    @Query(value = "SELECT * FROM subject_collection_parent_tbl WHERE prefix =:prefix AND delete_flag = false" , nativeQuery = true)
    Optional<SubjectCollectionParentEntity> getByPrefix(String prefix);
}
