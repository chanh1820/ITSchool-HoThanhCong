package com.nmc.itschool.repository;

import com.nmc.itschool.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    @Query(value ="SELECT * FROM note_tbl WHERE user_name=:userName AND title IS NOT NULL", nativeQuery = true)
    Optional<List<NoteEntity>> findByUserName(String userName);

    @Query(value ="SELECT * FROM note_tbl WHERE random_id=:randomId", nativeQuery = true)
    Optional<NoteEntity> findByRandomId(String randomId);
}