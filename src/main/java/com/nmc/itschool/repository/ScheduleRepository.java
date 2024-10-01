package com.nmc.itschool.repository;

import com.nmc.itschool.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer>{

    Optional<List<ScheduleEntity>> findAllByAccountId(Integer accountId);
}
