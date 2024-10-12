package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.TestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface TestMapper extends GeneralMapper<TestEntity, TestDTO>{

}
