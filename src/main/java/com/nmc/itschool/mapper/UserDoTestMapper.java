package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.UserDoTestDTO;
import com.nmc.itschool.entity.UserDoTestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface UserDoTestMapper extends GeneralMapper<UserDoTestEntity, UserDoTestDTO>{

}
