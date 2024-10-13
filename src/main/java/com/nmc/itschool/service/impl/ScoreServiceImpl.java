package com.nmc.itschool.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.QuestionItemDTO;
import com.nmc.itschool.dto.ScoreDTO;
import com.nmc.itschool.dto.UserDoTestDTO;
import com.nmc.itschool.entity.ScoreEntity;
import com.nmc.itschool.entity.TestEntity;
import com.nmc.itschool.entity.UserDoTestEntity;
import com.nmc.itschool.entity.UserEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.ScoreMapper;
import com.nmc.itschool.mapper.UserMapper;
import com.nmc.itschool.repository.ScoreRepository;
import com.nmc.itschool.repository.TestRepository;
import com.nmc.itschool.repository.UserDoTestRepository;
import com.nmc.itschool.repository.UserRepository;
import com.nmc.itschool.service.ScoreService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    UserDoTestRepository userDoTestRepository;
    @Autowired
    TestRepository testRepository;
    @Autowired
    UserMapper userMapper;

    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public ScoreDTO pushAnswerTest(UserDoTestDTO userDoTestDTO) {
        UserDoTestEntity userDoTestEntity = userDoTestRepository.findById(userDoTestDTO.getId())
                .orElseThrow(() -> new AppException(MessageEnum.ERR_TEST_NOT_FOUND));

        Optional<TestEntity> optTest = testRepository.findBySlug(userDoTestEntity.getSlug());
        Optional<UserEntity> userEntity = userRepository.findByUserName(userDoTestEntity.getUserName());
        if(optTest.isPresent()){
            TestEntity testEntity = optTest.get();
            float score = 0f;
            float scoreOfTest = (float) testEntity.getMaxPoint() / (testEntity.getNumberChooseTest()+testEntity.getNumberWriteTest());
            Integer numOfTrue = 0;
            Integer numOfFalse = 0;
            Integer numOfBlack = 0;
            Integer numOfError = 0;

            Map<String, QuestionItemDTO> questionItemDTOS = ObjectMapperUtil.readValue(testEntity.getJsonListItemQuestion(), new TypeReference<Map<String, QuestionItemDTO>>(){});
            Map<String, QuestionItemDTO> questionItemAnswer = ObjectMapperUtil.readValue(userDoTestEntity.getJsonListItemQuestion(), new TypeReference<Map<String, QuestionItemDTO>>(){});
            for (Map.Entry<String, QuestionItemDTO> entry : questionItemDTOS.entrySet()) {
                if(questionItemAnswer.containsKey(entry.getKey())){
                    QuestionItemDTO answer = questionItemAnswer.get(entry.getKey());
                    if(answer.getValue().equalsIgnoreCase(entry.getValue().getValue())){
                        score += scoreOfTest;
                        numOfTrue += 1;
                    }else {
                        numOfFalse += 1;
                    }
                }else {
                    numOfBlack += 1;
                }
            }
            ScoreEntity scoreEntity = new ScoreEntity();
            scoreEntity.setPoint(score);
            scoreEntity.setRandomId(UUID.randomUUID().toString());
            scoreEntity.setUserName(userDoTestEntity.getUserName());
            scoreEntity.setFullName(userEntity.get().getFullName());
            scoreEntity.setClassRoom(null);
            scoreEntity.setTestCode(testEntity.getTestCode());
            scoreEntity.setTestName(testEntity.getTestName());
            scoreEntity.setSlug(testEntity.getSlug());
            scoreEntity.setCreatedDate(LocalDateTime.now());
            scoreEntity.setMinuteTime(userDoTestEntity.getMinuteTime());
            scoreEntity.setPoint(score);
            ScoreEntity saved = scoreRepository.save(scoreEntity);
            return scoreMapper.toDTO(saved);
        }

        return null;
    }

    @Override
    public ScoreDTO findByRandomId(String randomId) {
        Optional<ScoreEntity> otp = scoreRepository.findByRandomId(randomId);
        if (otp.isPresent()){
            return scoreMapper.toDTO(otp.get());
        }
        return null;
    }
}
