package com.nmc.itschool.repository;

import com.nmc.itschool.dto.RateDTO;
import com.nmc.itschool.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RateRepository extends JpaRepository<RateEntity, Long> {

    @Query(value = "SELECT * FROM rate_tbl", nativeQuery = true)
    Optional<List<RateEntity>> getAll();

    @Query(value = "select avg(rate_value) from rate_tbl", nativeQuery = true)
    Float getAvg();
}