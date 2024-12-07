package com.nmc.itschool.service.impl;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.*;
import com.nmc.itschool.entity.NoteEntity;
import com.nmc.itschool.entity.QuickQuizEntity;
import com.nmc.itschool.entity.QuickQuizLogEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.NoteMapper;
import com.nmc.itschool.mapper.QuickQuizLogMapper;
import com.nmc.itschool.mapper.QuickQuizMapper;
import com.nmc.itschool.repository.NoteRepository;
import com.nmc.itschool.repository.QuickQuizLogRepository;
import com.nmc.itschool.repository.QuickQuizRepository;
import com.nmc.itschool.repository.UserRepository;
import com.nmc.itschool.service.QuickQuizService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class QuickQuizServiceImpl implements QuickQuizService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuickQuizRepository quickQuizRepository;

    @Autowired
    QuickQuizLogRepository quickQuizLogRepository;

    @Autowired
    QuickQuizMapper quickQuizMapper;

    @Autowired
    QuickQuizLogMapper quickQuizLogMapper;
    @Override
    public List<QuickQuizDTO> getByUserName(String userName) {
        List<QuickQuizDTO> quickQuizDTOS = new ArrayList<>();
        Optional<List<QuickQuizEntity>> optQuickQuizEntities = quickQuizRepository.findByUserName(userName);
        if(optQuickQuizEntities.isPresent() && CollectionUtils.isNotEmpty(optQuickQuizEntities.get())){
            quickQuizDTOS = quickQuizMapper.toDTOs((optQuickQuizEntities.get()));
        }else {
            log.info("getByUserName list empty");
        }
        return quickQuizDTOS;
    }

    @Override
    public QuickQuizDTO getPicked() {
        Optional<QuickQuizEntity> otp = quickQuizRepository.getPicked();
        if(otp.isPresent()){
            QuickQuizDTO quickQuizDTO = quickQuizMapper.toDTO(otp.get());
            return quickQuizDTO;
        }
        return null;
    }

    @Override
    public QuickQuizDTO findByRandomId(String randomId) {
        Optional<QuickQuizEntity> otp = quickQuizRepository.findByRandomId(randomId);
        if(otp.isPresent()){
            return quickQuizMapper.toDTO(otp.get());
        }
        return null;
    }

    @Override
    public QuickQuizDTO save(QuickQuizDTO quickQuizDTO) {
        QuickQuizEntity result = quickQuizRepository.save(quickQuizMapper.toEntity(quickQuizDTO));
        return quickQuizMapper.toDTO(result);
    }

    @Override
    public String init(String userName) {
        QuickQuizEntity quickQuizEntity = new QuickQuizEntity();
        quickQuizEntity.setRandomId(UUID.randomUUID().toString());
        quickQuizEntity.setUserName(userName);
        QuickQuizEntity result = quickQuizRepository.save(quickQuizEntity);
        return result.getRandomId();
    }

    @Override
    @Transactional
    public QuickQuizDTO enable(String randomId) {
        quickQuizRepository.disablePickedAll();
        QuickQuizDTO quickQuizDTO = new QuickQuizDTO();
        Optional<QuickQuizEntity> otp = quickQuizRepository.findByRandomId(randomId);
        if(otp.isPresent()){
            QuickQuizEntity quickQuizEntity = otp.get();
            quickQuizEntity.setPicked(true);
            quickQuizDTO = quickQuizMapper.toDTO(quickQuizRepository.save(quickQuizEntity));
        }
        return quickQuizDTO;
    }

    @Override
    public QuickQuizDTO disable(String randomId) {
//        quickQuizRepository.disablePickedAll();
        QuickQuizDTO quickQuizDTO = new QuickQuizDTO();
        Optional<QuickQuizEntity> otp = quickQuizRepository.findByRandomId(randomId);
        if(otp.isPresent()){
            QuickQuizEntity quickQuizEntity = otp.get();
            quickQuizEntity.setPicked(false);
            quickQuizDTO = quickQuizMapper.toDTO(quickQuizRepository.save(quickQuizEntity));
        }
        return quickQuizDTO;
    }

    @Override
    public boolean submitAnswer(QuickQuizAnswerDTO quickQuizAnswerDTO) {
        Optional<QuickQuizEntity> otpQuickQuiz = quickQuizRepository.findByRandomId(quickQuizAnswerDTO.getRandomId());
        QuickQuizLogEntity quickQuizLogEntity = new QuickQuizLogEntity();
        quickQuizLogEntity.setCreateDate(LocalDateTime.now());
        quickQuizLogEntity.setAnswer(quickQuizAnswerDTO.getAnswer());
        quickQuizLogEntity.setFullName(quickQuizAnswerDTO.getFullName());
        quickQuizLogEntity.setUserName(quickQuizAnswerDTO.getUserName());
        quickQuizLogEntity.setRandomId(quickQuizAnswerDTO.getRandomId());
        quickQuizLogEntity.setAnswerNumberTrue(quickQuizAnswerDTO.getAnswerNumberTrue());

        if(otpQuickQuiz.isPresent()){
            QuickQuizEntity quickQuizEntity = otpQuickQuiz.get();
            quickQuizLogEntity.setQuestion(quickQuizEntity.getQuestion());
            quickQuizLogEntity.setTitle(quickQuizEntity.getTitle());
            quickQuizLogEntity.setCorrect(quickQuizAnswerDTO.getAnswer().equalsIgnoreCase(quickQuizEntity.getResult()));
            quickQuizLogRepository.save(quickQuizLogEntity);
            return quickQuizAnswerDTO.getAnswer().equalsIgnoreCase(quickQuizEntity.getResult());
        }else {
            throw new AppException(MessageEnum.ERR_QUICK_QUIZ_NOT_FOUND);
        }
    }

    @Override
    public List<QuickQuizLogDTO> findQuickQuizLogs(String randomId) {
        List<QuickQuizLogDTO> quickQuizLogDTOS = new ArrayList<>();
        Optional<List<QuickQuizLogEntity>> otpQuickQuizLogs = quickQuizLogRepository.findQuickQuizLogs(randomId);
        if(otpQuickQuizLogs.isPresent() && CollectionUtils.isNotEmpty(otpQuickQuizLogs.get())){
            List<QuickQuizLogEntity> quickQuizLogEntities = otpQuickQuizLogs.get();
            quickQuizLogDTOS = quickQuizLogMapper.toDTOs(quickQuizLogEntities);
        }
        return quickQuizLogDTOS;
    }

    @Override
    public QuickQuizRankDTO calculatorRank(List<QuickQuizLogDTO> quickQuizLogDTOS) {
        long numberTrue = quickQuizLogDTOS.stream()
                .filter(QuickQuizLogDTO::isCorrect)  // Filters only items with isCorrect = true
                .count();
//        String personTrueTemplate = "Bạn kfyenasd đã có đáp đúng nhất";
//        StringBuilder personTrue = new StringBuilder();
//        int deltaCurrent = 0;
//        int deltaMin = 99999;
//        int onlyPerson = 0;
//        List<String> userNameTrued = new ArrayList<>();
//        List<QuickQuizLogDTO> quickQuizListName = new ArrayList<>();
//
//        for (QuickQuizLogDTO quickQuizLogDTO : quickQuizLogDTOS){
//            deltaCurrent =  Math.abs(quickQuizLogDTO.getAnswerNumberTrue() - (int) numberTrue);
//            log.info("deltaCurrent {}", deltaCurrent);
//
//            if(deltaCurrent < deltaMin){
//                deltaMin = deltaCurrent;
//            }
//        }
//        for (QuickQuizLogDTO quickQuizLogDTO : quickQuizLogDTOS){
//            log.info("quickQuizLogDTO {}", quickQuizLogDTO);
//            log.info("deltaCurrent {}", deltaCurrent + "--"+ deltaMin);
//            deltaCurrent =  Math.abs(quickQuizLogDTO.getAnswerNumberTrue() - (int) numberTrue);
//
//            if( deltaMin <= deltaCurrent  && !CollectionUtils.containsAny(userNameTrued, quickQuizLogDTO.getUserName())){
//                if(onlyPerson == 3){
//                    break;
//                }
//                personTrue.append(quickQuizLogDTO.getFullName());
//                userNameTrued.add(quickQuizLogDTO.getUserName());
//                if(onlyPerson != 0){
//                    personTrue.append(", ");
//                }
//                onlyPerson ++;
//
//            }
//        }
//        log.info("personTrue.toString() {} ", personTrue.toString());
//        personTrueTemplate = personTrueTemplate.replace("kfyenasd", personTrue);
        QuickQuizRankDTO quickQuizRankDTO = new QuickQuizRankDTO();
//        quickQuizRankDTO.setPersonTrue(personTrueTemplate);
        quickQuizRankDTO.setNumberPointTrue("Số người trả lời đúng: " + numberTrue);
        return quickQuizRankDTO;
    }

    @Override
    public void deleteById(Long id) {
        quickQuizRepository.deleteById(id);
    }
}
