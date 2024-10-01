package com.nmc.itschool.service.impl;


import com.nmc.itschool.dto.LessonCollectionParentDTO;
import com.nmc.itschool.dto.ScheduleDTO;
import com.nmc.itschool.entity.LessonCollectionParentEntity;
import com.nmc.itschool.entity.ScheduleEntity;
import com.nmc.itschool.mapper.LessonCollectionParentMapper;
import com.nmc.itschool.mapper.ScheduleMapper;
import com.nmc.itschool.repository.LessonCollectionParentRepository;
import com.nmc.itschool.repository.ScheduleRepository;
import com.nmc.itschool.sco.ScheduleSCO;
import com.nmc.itschool.service.LessonCollectionParentService;
import com.nmc.itschool.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LessonCollectionParentServiceImpl implements LessonCollectionParentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LessonCollectionParentServiceImpl.class);

    @Autowired
    LessonCollectionParentRepository lessonCollectionParentRepository;

    @Autowired
    LessonCollectionParentMapper lessonCollectionParentMapper;


    @Override
    public List<LessonCollectionParentDTO> getAll() {
        Optional<List<LessonCollectionParentEntity>> optional = lessonCollectionParentRepository.getAll();
        return optional.map(lessonCollectionParentEntities -> lessonCollectionParentMapper.toDTOs(lessonCollectionParentEntities)).orElse(null);
    }

}
