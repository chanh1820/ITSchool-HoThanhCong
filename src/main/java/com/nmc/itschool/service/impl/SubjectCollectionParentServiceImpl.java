package com.nmc.itschool.service.impl;


import com.nmc.itschool.constant.DBConstant;
import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import com.nmc.itschool.mapper.SubjectCollectionParentMapper;
import com.nmc.itschool.repository.SubjectCollectionParentRepository;
import com.nmc.itschool.service.SubjectCollectionParentService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<SubjectCollectionParentDTO> getAllInHome() {
        List<SubjectCollectionParentEntity> result = new ArrayList<>();
        Optional<List<SubjectCollectionParentEntity>> optional = subjectCollectionParentRepository.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_LESSON);
        if (optional.isPresent() && CollectionUtils.isNotEmpty(optional.get())){
            result = optional.get();
            result.removeIf(
                    entity -> (entity.getSubjectCollectionParentCode().equals(DBConstant.SUBJECT_COLLECTION_PARENT_CODE_KHAC))
            );
        }

        return subjectCollectionParentMapper.toDTOs(result);
    }

    @Override
    public List<SubjectCollectionParentDTO> getAllByType(String type) {
        List<SubjectCollectionParentEntity> result = new ArrayList<>();
        Optional<List<SubjectCollectionParentEntity>> optional = subjectCollectionParentRepository.getAllByType(type);
        if (optional.isPresent() && CollectionUtils.isNotEmpty(optional.get())){
            result = optional.get();
            return subjectCollectionParentMapper.toDTOs(result);

        }
        return null;
    }

}
