package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.TestEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface TestMapper extends GeneralMapper<TestEntity, TestDTO>{

}
