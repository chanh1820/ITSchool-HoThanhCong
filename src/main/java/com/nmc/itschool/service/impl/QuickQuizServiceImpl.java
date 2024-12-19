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
import java.util.stream.Collectors;

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
        int target = (int) numberTrue; // Target number
        int n = 3; // Number of closest objects to find

        List<QuickQuizLogDTO> closest = findClosest(quickQuizLogDTOS, target, n);
        System.out.println("Closest " + n + " objects to " + target + ": " + closest);
        List<String> personTrueStr = closest.stream()
                .map(QuickQuizLogDTO::getFullName)
                .collect(Collectors.toList());
        String personTrue = String.join(", ", personTrueStr);
        QuickQuizRankDTO quickQuizRankDTO = new QuickQuizRankDTO();
        quickQuizRankDTO.setPersonTrue("Xin chúc mừng bạn: " + personTrue+ " đã có dự đoán chính xác nhất");
        quickQuizRankDTO.setNumberPointTrue("Số người trả lời đúng: " + numberTrue );
        return quickQuizRankDTO;
    }

    @Override
    public void deleteById(Long id) {
        quickQuizRepository.deleteById(id);
    }

    public List<QuickQuizLogDTO> findClosest(List<QuickQuizLogDTO> list, int target, int n) {
        // Sort the list based on the absolute difference between `answerNumberTrue` and the target
        list.sort(Comparator.comparingInt(o -> Math.abs(o.getAnswerNumberTrue() - target)));

        // Return the first `n` elements
        return list.subList(0, Math.min(n, list.size()));
    }
}
