package com.nmc.itschool.repository;

import com.demo.crudemployee.entity.EmployeeEntity;
import com.demo.crudemployee.repository.custom.EmployeeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID>, EmployeeRepositoryCustom {

}
