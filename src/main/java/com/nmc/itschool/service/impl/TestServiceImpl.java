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
        TestEntity testEntity = null;
        TestEntity result;
        Optional<TestEntity> otp = testRepository.findById(testDTO.getId());
        if (otp.isPresent()){
            testEntity = otp.get();
            testEntity.setJsonListItemQuestion(testDTO.getJsonListItemQuestion());
            result = testRepository.save(testEntity);
        }else {
            throw new AppException(MessageEnum.ERR_TEST_NOT_FOUND);
        }

        return testMapper.toDTO(result);
    }

    @Override
    public TestDTO findBySlug(String slug) {
        Optional<TestEntity> otp = testRepository.findBySlug(slug);
        if (otp.isPresent()){
            return testMapper.toDTO(otp.get());
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

    @Override
    public void deleteById(Long id) {
        testRepository.deleteById(id);
    }

    @Override
    public List<TestDTO> findByPrefix(String prefix) {
        Optional<List<TestEntity>> otp = testRepository.findByPrefix(prefix);
        if (otp.isPresent() && otp.get().size() != 0){
            return testMapper.toDTOs(otp.get());
        }
        return null;    }

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
