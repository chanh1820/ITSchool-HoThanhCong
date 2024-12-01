package com.nmc.itschool.service.impl;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.*;
import com.nmc.itschool.entity.*;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.LessonMapper;
import com.nmc.itschool.mapper.TestCollectionMapper;
import com.nmc.itschool.mapper.TestMapper;
import com.nmc.itschool.mapper.TestResultMapper;
import com.nmc.itschool.repository.*;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.service.TestService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    TestRepository testRepository;
    @Autowired
    TestCollectionRepository testCollectionRepository;
    @Autowired
    TestResultRepository testResultRepository;
    @Autowired
    SubjectCollectionParentRepository subjectCollectionParentRepository;
    @Autowired
    SubjectCollectionRepository subjectCollectionRepository;
    @Autowired
    TestMapper testMapper;
    @Autowired
    TestCollectionMapper testCollectionMapper;
    @Autowired
    TestResultMapper testResultMapper;
    @Override
    public TestDTO save(TestDTO testDTO) {
        log.info(ObjectMapperUtil.writeValueAsString(testDTO));
        validateSaveLesson(testDTO.getCollectionParentPrefix(), testDTO.getCollectionPrefix());
        testDTO.setCreatedDate(LocalDateTime.now());
        testDTO.setUpdatedDate(LocalDateTime.now());
        testDTO.setDeleteFlag(false);
        TestEntity result = testRepository.save(testMapper.toEntity(testDTO));
        return testMapper.toDTO(result);
    }

    @Override
    public TestDTO findById(Long id) {
        Optional<TestEntity> otp = testRepository.findById(id);
        if(otp.isPresent()){
            return testMapper.toDTO(otp.get());
        }
        return null;
    }

    @Override
    @Transactional
    public List<TestResultDTO> saveResultItem(TestResultSaveDTO testResultSaveDTO) {
        log.info(ObjectMapperUtil.writeValueAsString(testResultSaveDTO));
        boolean result;
        Optional<TestEntity> otp = testRepository.findById(testResultSaveDTO.getTestId());
        if (otp.isPresent()){
            List<TestResultDTO> chooseList = testResultSaveDTO.getAnswerChooseList();
            List<TestResultDTO> writeList = testResultSaveDTO.getAnswerWriteList();
            TestEntity testEntity = otp.get();
            testEntity.setIsAvailable(true);

            List<TestResultDTO> testResultDTOS = new ArrayList<>();
            testResultDTOS.addAll(chooseList);
            testResultDTOS.addAll(writeList);
            int rowsAffected = testResultRepository.deleteByTestId(testResultSaveDTO.getTestId());
            log.info("deleted {} test result item  by testId = {}", rowsAffected, testResultSaveDTO.getTestId());
            List<TestResultEntity> testResultEntities = testResultRepository.saveAll(testResultMapper.toEntities(testResultDTOS));
            return testResultMapper.toDTOs(testResultEntities);
        }else {
            throw new AppException(MessageEnum.ERR_TEST_NOT_FOUND);
        }
    }

    @Override
    public List<TestResultDTO> getResultItem(Long testId) {
        Optional<List<TestResultEntity>> optional = testResultRepository.findByTestId(testId);
        if(optional.isPresent()){
            return testResultMapper.toDTOs(optional.get());
        }
        return null;
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
    public List<TestDTO> getTestByCollectionUUID(String uuid) {
        List<TestDTO> testDTOS = new ArrayList<>();
        Optional<List<TestEntity>> otp = testRepository.findByCollectionUUID(uuid);
        if (CollectionUtils.isNotEmpty(otp.get()) || otp.get().size() != 0) {
            testDTOS = testMapper.toDTOs(otp.get());
            return testDTOS;
        }
        return testDTOS;
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

    @Override
    public TestCollectionDTO saveCollection(TestCollectionDTO testCollectionDTO) {
        log.info(ObjectMapperUtil.writeValueAsString(testCollectionDTO));
        validateSaveLesson(testCollectionDTO.getCollectionParentPrefix(), testCollectionDTO.getCollectionPrefix());
        testCollectionDTO.setCreatedDate(LocalDateTime.now());
        testCollectionDTO.setUpdatedDate(LocalDateTime.now());
        testCollectionDTO.setDeleteFlag(false);
        TestCollectionEntity result = testCollectionRepository.save(testCollectionMapper.toEntity(testCollectionDTO));
        return testCollectionMapper.toDTO(result);    }

    @Override
    public List<TestCollectionDTO> getCollectionAll(int limit) {
        List<TestCollectionDTO> result = new ArrayList<>();
        Optional<List<TestCollectionEntity>> otp = testCollectionRepository.getAll(limit);
        if (otp.isPresent() && otp.get().size() != 0){
            return testCollectionMapper.toDTOs(otp.get());
        }
        return result;
    }

    @Override
    public TestCollectionDTO getCollectionByUUID(String uuid) {
        Optional<TestCollectionEntity> otp = testCollectionRepository.findByUUID(uuid);
        if(otp.isPresent()){
            TestCollectionEntity testCollectionEntity = otp.get();
            Optional<List<TestEntity>> optionalTestEntities = testRepository.findByCollectionUUID(testCollectionEntity.getTestCollectionUUID());
            if(optionalTestEntities.isPresent()){
                testCollectionEntity.setTestEntityList(optionalTestEntities.get());
            }
            return testCollectionMapper.toDTO(testCollectionEntity);
        }
        return null;
    }

    @Override
    public TestCollectionDTO getCollectionBySlug(String slug) {
        Optional<TestCollectionEntity> otp = testCollectionRepository.findBySlug(slug);
        if(otp.isPresent()){
            TestCollectionEntity testCollectionEntity = otp.get();
            Optional<List<TestEntity>> optionalTestEntities = testRepository.findByCollectionUUID(testCollectionEntity.getTestCollectionUUID());
            if(optionalTestEntities.isPresent()){
                testCollectionEntity.setTestEntityList(optionalTestEntities.get());
            }
            return testCollectionMapper.toDTO(testCollectionEntity);
        }
        return null;    }

    @Override
    public void deleteCollectionById(Long id) {
        testCollectionRepository.deleteById(id);
    }

    private void validateSaveLesson(String parentPrefix, String childPrefix) {
        Optional<SubjectCollectionParentEntity> optParentCollection = subjectCollectionParentRepository.getByPrefix(parentPrefix);
        if (!optParentCollection.isPresent()){
            throw new AppException(MessageEnum.ERR_PREFIX_PARENT_NOT_MATCH);
        }
        Optional<SubjectCollectionEntity> optChildCollection = subjectCollectionRepository.getByPrefix(childPrefix);
        if (!optChildCollection.isPresent()){
            throw new AppException(MessageEnum.ERR_PREFIX_CHILD_NOT_MATCH);
        }
    }


}
