package com.nmc.itschool.service.impl;

import com.nmc.itschool.dto.NoteDTO;
import com.nmc.itschool.dto.QuickQuizDTO;
import com.nmc.itschool.entity.NoteEntity;
import com.nmc.itschool.entity.QuickQuizEntity;
import com.nmc.itschool.mapper.NoteMapper;
import com.nmc.itschool.mapper.QuickQuizMapper;
import com.nmc.itschool.repository.NoteRepository;
import com.nmc.itschool.repository.QuickQuizRepository;
import com.nmc.itschool.repository.UserRepository;
import com.nmc.itschool.service.QuickQuizService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class QuickQuizServiceImpl implements QuickQuizService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    QuickQuizRepository quickQuizRepository;
    @Autowired
    QuickQuizMapper quickQuizMapper;

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
}
