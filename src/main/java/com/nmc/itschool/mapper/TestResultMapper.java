package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.TestResultDTO;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.TestResultEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TestResultMapper extends GeneralMapper<TestResultEntity, TestResultDTO>{

}
