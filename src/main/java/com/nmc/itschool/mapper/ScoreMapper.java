package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.ScoreDTO;
import com.nmc.itschool.entity.ScoreEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ScoreMapper extends GeneralMapper<ScoreEntity, ScoreDTO>{

}
