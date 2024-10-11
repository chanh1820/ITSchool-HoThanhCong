package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.UserDTO;
import com.nmc.itschool.entity.UserEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper extends GeneralMapper<UserEntity, UserDTO>{

}
