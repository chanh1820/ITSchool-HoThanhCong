package com.nmc.itschool.service;

import com.demo.crudemployee.dto.EmployeeDTO;
import com.demo.crudemployee.entity.EmployeeEntity;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    public List<EmployeeDTO> findAll();

    public EmployeeDTO findById(UUID id);

    public EmployeeEntity save(EmployeeDTO employeeDTO);

    public EmployeeEntity update(EmployeeDTO employeeDTO);

    public void delete(UUID id);

//    public List<EmployeeDTO> searchEmployee(EmployeeSCO employeeSCO);

}
