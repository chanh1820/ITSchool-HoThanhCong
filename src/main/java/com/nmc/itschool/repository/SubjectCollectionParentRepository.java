package com.nmc.itschool.repository;

import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectCollectionParentRepository extends JpaRepository<SubjectCollectionParentEntity, Long>{

    @Query(value = "SELECT * FROM subject_collection_parent_tbl WHERE type =:type AND delete_flag = false ORDER BY sort_order ASC" , nativeQuery = true)
    Optional<List<SubjectCollectionParentEntity>> getAllByType(String type);

    @Query(value = "SELECT * FROM subject_collection_parent_tbl WHERE prefix =:prefix AND delete_flag = false ORDER BY sort_order ASC" , nativeQuery = true)
    Optional<SubjectCollectionParentEntity> getByPrefix(String prefix);
}
