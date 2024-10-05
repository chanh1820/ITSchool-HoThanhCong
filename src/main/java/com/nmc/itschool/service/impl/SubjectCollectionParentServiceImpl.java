package com.nmc.itschool.service.impl;


import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import com.nmc.itschool.mapper.SubjectCollectionParentMapper;
import com.nmc.itschool.repository.SubjectCollectionParentRepository;
import com.nmc.itschool.service.SubjectCollectionParentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SubjectCollectionParentServiceImpl implements SubjectCollectionParentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectCollectionParentServiceImpl.class);

    @Autowired
    SubjectCollectionParentRepository subjectCollectionParentRepository;

    @Autowired
    SubjectCollectionParentMapper subjectCollectionParentMapper;


    @Override
    public List<SubjectCollectionParentDTO> getAll() {
        Optional<List<SubjectCollectionParentEntity>> optional = subjectCollectionParentRepository.getAll();
        return optional.map(subjectCollectionParentEntities -> subjectCollectionParentMapper.toDTOs(subjectCollectionParentEntities)).orElse(null);
    }

}
