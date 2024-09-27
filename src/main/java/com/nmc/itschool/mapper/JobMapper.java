package com.nmc.itschool.mapper;

import com.demo.crudemployee.dto.JobDTO;
import com.demo.crudemployee.entity.JobEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface JobMapper extends GeneralMapper<JobEntity, JobDTO>{
    JobDTO toDTO(JobEntity entity);

    JobEntity toEntity(JobDTO dto);
}
