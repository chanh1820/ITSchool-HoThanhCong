package com.nmc.itschool.repository;

import com.nmc.itschool.entity.TestResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestResultRepository extends JpaRepository<TestResultEntity, Long>{
    @Query(value = "SELECT * FROM test_result_tbl WHERE test_id=:testId ORDER BY sort_oder ASC " , nativeQuery = true)
    Optional<List<TestResultEntity>> findByTestId(Long testId);

    @Modifying
    @Query(value = "DELETE FROM test_result_tbl WHERE test_id = :testId", nativeQuery = true)
    int deleteByTestId(Long testId);
}
