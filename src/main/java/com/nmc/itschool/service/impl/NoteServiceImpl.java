package com.nmc.itschool.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.*;
import com.nmc.itschool.entity.*;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.NoteMapper;
import com.nmc.itschool.mapper.ScoreMapper;
import com.nmc.itschool.mapper.UserMapper;
import com.nmc.itschool.repository.*;
import com.nmc.itschool.service.NoteService;
import com.nmc.itschool.service.ScoreService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class NoteServiceImpl implements NoteService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    NoteRepository noteRepository;
    @Autowired
    NoteMapper noteMapper;

    @Override
    public List<NoteDTO> getByUserName(String userName) {
        List<NoteDTO> noteDTOS = new ArrayList<>();
        Optional<List<NoteEntity>> optNoteEntities = noteRepository.findByUserName(userName);
        if(optNoteEntities.isPresent() && CollectionUtils.isNotEmpty(optNoteEntities.get())){
            noteDTOS = noteMapper.toDTOs((optNoteEntities.get()));
        }else {
            log.info("getByUserName list empty");
        }
        return noteDTOS;
    }

    @Override
    public NoteDTO findByRandomId(String randomId) {
        Optional<NoteEntity> otp = noteRepository.findByRandomId(randomId);
        if(otp.isPresent()){
            return noteMapper.toDTO(otp.get());
        }
        return null;
    }

    @Override
    public NoteDTO save(NoteDTO noteDTO) {
        NoteEntity result = noteRepository.save(noteMapper.toEntity(noteDTO));
        return noteMapper.toDTO(result);
    }

    @Override
    public String init(String userName) {
        NoteEntity noteEntity = new NoteEntity();
        noteEntity.setRandomId(UUID.randomUUID().toString());
        noteEntity.setUserName(userName);
        NoteEntity result = noteRepository.save(noteEntity);
        return result.getRandomId();
    }
}
