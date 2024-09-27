package com.nmc.itschool.mapper;

import com.demo.crudemployee.dto.DepartmentDTO;
import com.demo.crudemployee.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DepartmentMapper extends GeneralMapper<DepartmentEntity, DepartmentDTO>{
    DepartmentDTO toDTO(DepartmentEntity entity);

    DepartmentEntity toEntity(DepartmentDTO dto);
}
