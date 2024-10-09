package com.nmc.itschool.service.impl;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.QuestionItemDTO;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import com.nmc.itschool.entity.TestEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.LessonMapper;
import com.nmc.itschool.mapper.TestMapper;
import com.nmc.itschool.repository.LessonRepository;
import com.nmc.itschool.repository.SubjectCollectionParentRepository;
import com.nmc.itschool.repository.SubjectCollectionRepository;
import com.nmc.itschool.repository.TestRepository;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.service.TestService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;

    @Autowired
    SubjectCollectionParentRepository subjectCollectionParentRepository;

    @Autowired
    SubjectCollectionRepository subjectCollectionRepository;

    @Autowired
    TestMapper testMapper;
    @Override
    public TestDTO save(TestDTO testDTO) {
        log.info(ObjectMapperUtil.writeValueAsString(testDTO));
        validateSaveLesson(testDTO);
        testDTO.setCreatedDate(LocalDateTime.now());
        testDTO.setUpdatedDate(LocalDateTime.now());
        testDTO.setDeleteFlag(false);
        TestEntity result = testRepository.save(testMapper.toEntity(testDTO));
        return testMapper.toDTO(result);
    }

    @Override
    public TestDTO saveItem(TestDTO testDTO) {
        log.info(ObjectMapperUtil.writeValueAsString(testDTO));
        log.info(ObjectMapperUtil.writeValueAsString(testMapper.toEntity(testDTO)));
        TestEntity result = testRepository.save(testMapper.toEntity(testDTO));
        return testMapper.toDTO(result);
    }

    @Override
    public TestDTO findBySlug(String slug) {
        Optional<List<TestEntity>> otp = testRepository.findBySlug(slug);
        if (otp.isPresent()){
            return testMapper.toDTO(otp.get().get(0));
        }else {
            return null;
        }
    }

    @Override
    public List<TestDTO> getAll(int limit) {
        Optional<List<TestEntity>> otp = testRepository.getAll(limit);
        if (otp.isPresent()){
            return testMapper.toDTOs(otp.get());
        }
        return null;
    }

    private void validateSaveLesson(TestDTO testDTO) {
        Optional<SubjectCollectionParentEntity> optParentCollection = subjectCollectionParentRepository.getByPrefix(testDTO.getCollectionParentPrefix());
        if (!optParentCollection.isPresent()){
            throw new AppException(MessageEnum.ERR_PREFIX_PARENT_NOT_MATCH);
        }
        Optional<SubjectCollectionEntity> optChildCollection = subjectCollectionRepository.getByPrefix(testDTO.getCollectionPrefix());
        if (!optChildCollection.isPresent()){
            throw new AppException(MessageEnum.ERR_PREFIX_CHILD_NOT_MATCH);
        }
    }


}
