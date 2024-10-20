package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.NoteDTO;
import com.nmc.itschool.entity.NoteEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface NoteMapper extends GeneralMapper<NoteEntity, NoteDTO>{

}
