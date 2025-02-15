package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.RateDTO;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.RateEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RateMapper extends GeneralMapper<RateEntity, RateDTO>{

}
