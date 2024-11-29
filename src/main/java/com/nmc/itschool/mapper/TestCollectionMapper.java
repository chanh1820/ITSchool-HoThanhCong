package com.nmc.itschool.mapper;


import com.nmc.itschool.dto.TestCollectionDTO;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.entity.TestCollectionEntity;
import com.nmc.itschool.entity.TestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;


@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS )
public interface TestCollectionMapper extends GeneralMapper<TestCollectionEntity, TestCollectionDTO>{

}
