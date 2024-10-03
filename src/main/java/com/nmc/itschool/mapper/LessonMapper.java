package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.entity.LessonEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LessonMapper extends GeneralMapper<LessonEntity, LessonDTO>{

}
