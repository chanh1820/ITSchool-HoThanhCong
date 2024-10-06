package com.nmc.itschool.mapper;

import com.nmc.itschool.dto.SubjectCollectionDTO;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubjectCollectionMapper extends GeneralMapper<SubjectCollectionEntity, SubjectCollectionDTO>{

}
