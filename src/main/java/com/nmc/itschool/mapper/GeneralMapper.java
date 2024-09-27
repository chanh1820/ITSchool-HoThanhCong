package com.nmc.itschool.mapper;

import java.util.List;

public interface GeneralMapper <Entity, DTO> {

    Entity toEntity(DTO dto);

    DTO toDTO(Entity entity);

    List<DTO> toDTOs(List<Entity> entities);

    List<Entity> toEntities(List<DTO> dtos);
}
