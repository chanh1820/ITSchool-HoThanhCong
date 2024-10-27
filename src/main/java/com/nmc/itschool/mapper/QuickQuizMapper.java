package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.QuickQuizDTO;
import com.nmc.itschool.entity.QuickQuizEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface QuickQuizMapper extends GeneralMapper< QuickQuizEntity,  QuickQuizDTO>{

}
