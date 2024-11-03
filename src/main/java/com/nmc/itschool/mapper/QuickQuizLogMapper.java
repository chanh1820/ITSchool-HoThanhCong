package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.QuickQuizLogDTO;
import com.nmc.itschool.entity.QuickQuizLogEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface QuickQuizLogMapper extends GeneralMapper<QuickQuizLogEntity, QuickQuizLogDTO>{

}
