package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.ScheduleDTO;
import com.nmc.itschool.entity.ScheduleEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ScheduleMapper extends GeneralMapper<ScheduleEntity, ScheduleDTO>{

}
