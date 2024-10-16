package com.nmc.itschool.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.QuestionItemDTO;
import com.nmc.itschool.dto.ScoreDTO;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.dto.UserDoTestDTO;
import com.nmc.itschool.entity.*;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.ScoreMapper;
import com.nmc.itschool.mapper.TestMapper;
import com.nmc.itschool.mapper.UserDoTestMapper;
import com.nmc.itschool.repository.*;
import com.nmc.itschool.service.TestService;
import com.nmc.itschool.service.UserDoTestService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserDoTestServiceImpl implements UserDoTestService {

    @Autowired
    TestRepository testRepository;
    @Autowired
    UserDoTestRepository userDoTestRepository;
    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SubjectCollectionParentRepository subjectCollectionParentRepository;
    @Autowired
    SubjectCollectionRepository subjectCollectionRepository;
    @Autowired
    TestMapper testMapper;
    @Autowired
    UserDoTestMapper userDoTestMapper;
    @Autowired
    ScoreMapper scoreMapper;
    @Override
    public UserDoTestDTO findByUserNameAndSlug(String userName, String slug) {
        Optional<List<UserDoTestEntity>> otp = userDoTestRepository.findBySlugAndUserName(userName, slug);
        log.info("otp.get().size() - 1: {} {}", userName, slug);
        if(otp.isPresent() && CollectionUtils.isNotEmpty(otp.get())){
            log.info("otp.get().size() - 1: {}", otp.get().size() - 1);
            UserDoTestEntity userDoTestEntity = otp.get().get(otp.get().size() - 1);
            return userDoTestMapper.toDTO(userDoTestEntity);
        }

        return null;
    }

    @Override
    public UserDoTestDTO save(UserDoTestDTO userDoTestDTO) {
        String userName = userDoTestDTO.getUserName();
        String slug = userDoTestDTO.getSlug();
        Optional<TestEntity> otp = testRepository.findBySlug(slug);
        if(otp.isPresent()){
            TestEntity testEntity = otp.get();
            UserDoTestEntity userDoTestVO = new UserDoTestEntity();
            userDoTestVO.setUserName(userName);
            userDoTestVO.setSlug(slug);
            userDoTestVO.setJsonListItemQuestion(testEntity.getJsonListItemQuestion());
            userDoTestVO.setMinuteTime(testEntity.getMinuteTime());
            userDoTestVO.setDeleteFlag(false);
            UserDoTestEntity entity = userDoTestRepository.save(userDoTestVO);

            return userDoTestMapper.toDTO(entity);
        }
        return null;
    }

    @Override
    public UserDoTestDTO updateReadTime(UserDoTestDTO userDoTestDTO) {
        Long id = userDoTestDTO.getId();

        Optional<UserDoTestEntity> otp = userDoTestRepository.findById(id);
        if (otp.isPresent()){
            UserDoTestEntity userDoTestEntity = otp.get();
            userDoTestEntity.setJsonListItemQuestion(userDoTestDTO.getJsonListItemQuestion());
            userDoTestEntity.setMinuteTime(userDoTestDTO.getMinuteTime());

            UserDoTestEntity result = userDoTestRepository.save(userDoTestEntity);
            return userDoTestMapper.toDTO(result);
        }
        return null;
    }
}
