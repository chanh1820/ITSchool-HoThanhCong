package com.nmc.itschool.mapper;

import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectCollectionParentMapper extends GeneralMapper<SubjectCollectionParentEntity, SubjectCollectionParentDTO>{

}
