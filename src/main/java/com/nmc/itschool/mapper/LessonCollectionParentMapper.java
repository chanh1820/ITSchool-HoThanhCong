package com.nmc.itschool.mapper;

import com.nmc.itschool.dto.LessonCollectionParentDTO;
import com.nmc.itschool.entity.LessonCollectionParentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonCollectionParentMapper extends GeneralMapper<LessonCollectionParentEntity, LessonCollectionParentDTO>{

}
