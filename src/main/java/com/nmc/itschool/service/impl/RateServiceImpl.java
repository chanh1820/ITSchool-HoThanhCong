package com.nmc.itschool.service.impl;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.*;
import com.nmc.itschool.entity.QuickQuizEntity;
import com.nmc.itschool.entity.QuickQuizLogEntity;
import com.nmc.itschool.entity.RateEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.QuickQuizLogMapper;
import com.nmc.itschool.mapper.QuickQuizMapper;
import com.nmc.itschool.mapper.RateMapper;
import com.nmc.itschool.repository.QuickQuizLogRepository;
import com.nmc.itschool.repository.QuickQuizRepository;
import com.nmc.itschool.repository.RateRepository;
import com.nmc.itschool.repository.UserRepository;
import com.nmc.itschool.service.QuickQuizService;
import com.nmc.itschool.service.RateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RateRepository rateRepository;

    @Autowired
    RateMapper rateMapper;

    @Override
    public RateDTO save(RateDTO rateDTO) {
        RateEntity rateEntity = rateMapper.toEntity(rateDTO);
        RateEntity resultEntity = rateRepository.save(rateEntity);
        return rateMapper.toDTO(resultEntity);
    }

    @Override
    public List<RateDTO> getAll() {
        List<RateDTO> result = new ArrayList<>();
        Optional<List<RateEntity>> otp = rateRepository.getAll();
        if(otp.isPresent() && CollectionUtils.isNotEmpty(otp.get())){
            result = rateMapper.toDTOs(otp.get());
            return result;
        }
        return result;
    }

    @Override
    public Float getAvg() {
        return rateRepository.getAvg();
    }
}
