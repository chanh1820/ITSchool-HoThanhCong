package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.LessonPDFEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LessonPDFMapper extends GeneralMapper<LessonPDFEntity, LessonDTO>{

}
