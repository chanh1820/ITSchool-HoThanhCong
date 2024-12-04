package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.NewsDTO;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.NewsEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface NewsMapper extends GeneralMapper<NewsEntity, NewsDTO>{

}
