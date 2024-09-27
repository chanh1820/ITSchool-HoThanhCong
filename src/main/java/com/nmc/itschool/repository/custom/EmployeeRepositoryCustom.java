package com.nmc.itschool.repository.custom;

import com.demo.crudemployee.entity.EmployeeEntity;
import com.demo.crudemployee.repository.BaseRepository;

import java.util.UUID;

public interface EmployeeRepositoryCustom extends BaseRepository<EmployeeEntity, UUID> {
    
//    List<EmployeeEntity> searchEmployee(EmployeeSCO employeeSCO);

}
