package com.nmc.itschool.service.impl;

import com.demo.crudemployee.constant.MessageEnum;
import com.demo.crudemployee.dto.EmployeeDTO;
import com.demo.crudemployee.entity.EmployeeEntity;
import com.demo.crudemployee.exceptions.AppException;
import com.demo.crudemployee.mapper.EmployeeMapper;
import com.demo.crudemployee.repository.EmployeeRepository;
import com.demo.crudemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeMapper.toDTOs(employeeRepository.findAll());
    }

    @Override
    @Transactional
    public EmployeeDTO findById(UUID id) {
        Optional<EmployeeEntity> otp = employeeRepository.findEntityById(id);

        if (!otp.isPresent()) {
            throw new AppException(MessageEnum.ERR_EMPLOYEE_NOT_FOUND);
        }

        return employeeMapper.toDTO(otp.get());
    }

    @Override
    public EmployeeEntity save(EmployeeDTO employeeDTO) {
        return employeeRepository.save(employeeMapper.toEntity(employeeDTO));
    }

    @Override
    public EmployeeEntity update(EmployeeDTO employeeDTO) {
        Optional<EmployeeEntity> optional = employeeRepository.findEntityById(employeeDTO.getId());

        if (!optional.isPresent()) {
            throw new AppException(MessageEnum.ERR_EMPLOYEE_NOT_FOUND);
        }

        return employeeRepository.save(employeeMapper.toEntity(employeeDTO));
    }

    @Override
    public void delete(UUID id) {
        Optional<EmployeeEntity> optional = employeeRepository.findEntityById(id);

        if (!optional.isPresent()) {
            throw new AppException(MessageEnum.ERR_EMPLOYEE_NOT_FOUND);
        }

        EmployeeEntity employeeEntity = optional.get();

        employeeEntity.setDeleted(true);

        employeeRepository.save(employeeEntity);
    }

//    @Override
//    public List<EmployeeDTO> searchEmployee(EmployeeSCO employeeSCO) {
//        return employeeMapper.toDTOs(employeeRepository.searchEmployee(employeeSCO));
//    }

}
