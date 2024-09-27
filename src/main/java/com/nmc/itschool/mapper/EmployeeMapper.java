package com.nmc.itschool.mapper;

import com.demo.crudemployee.dto.EmployeeDTO;
import com.demo.crudemployee.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
        uses = {DepartmentMapper.class, JobMapper.class})
public interface EmployeeMapper extends GeneralMapper<EmployeeEntity, EmployeeDTO>{
    @Mapping(source = "departmentEntity", target = "departmentDTO", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(source = "jobEntity", target = "jobDTO", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
//    @Mapping(source = "managerEntity", target = "managerDTO", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    EmployeeDTO toDTO(EmployeeEntity entity);

    @Mapping(source = "departmentDTO", target = "departmentEntity")
    @Mapping(source = "jobDTO", target = "jobEntity")
//    @Mapping(source = "managerDTO", target = "managerEntity")
    EmployeeEntity toEntity(EmployeeDTO dto);

}
