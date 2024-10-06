package com.nmc.itschool.service.impl;


import com.nmc.itschool.dto.SubjectCollectionDTO;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import com.nmc.itschool.mapper.SubjectCollectionMapper;
import com.nmc.itschool.repository.SubjectCollectionRepository;
import com.nmc.itschool.service.SubjectCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SubjectCollectionServiceImpl implements SubjectCollectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectCollectionServiceImpl.class);

    @Autowired
    SubjectCollectionRepository subjectCollectionRepository;

    @Autowired
    SubjectCollectionMapper subjectCollectionMapper;


    @Override
    public List<SubjectCollectionDTO> getAll() {
        Optional<List<SubjectCollectionEntity>> optional = subjectCollectionRepository.getAll();
        return optional.map(subjectCollectionEntities -> subjectCollectionMapper.toDTOs(subjectCollectionEntities)).orElse(null);
    }

}
