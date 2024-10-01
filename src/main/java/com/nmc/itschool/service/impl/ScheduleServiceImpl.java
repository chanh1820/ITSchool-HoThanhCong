package com.nmc.itschool.service.impl;


import com.nmc.itschool.dto.ScheduleDTO;
import com.nmc.itschool.entity.ScheduleEntity;
import com.nmc.itschool.mapper.ScheduleMapper;
import com.nmc.itschool.repository.ScheduleRepository;
import com.nmc.itschool.sco.ScheduleSCO;
import com.nmc.itschool.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleMapper scheduleMapper;


    @Override
    public List<ScheduleDTO> getAllByAccountId(ScheduleSCO scheduleSCO) {
        Optional<List<ScheduleEntity>> optional = scheduleRepository.findAllByAccountId(scheduleSCO.getAccountId());
        return optional.map(scheduleEntities -> scheduleMapper.toDTOs(scheduleEntities)).orElse(null);
    }

    @Override
    public ScheduleDTO save(ScheduleDTO scheduleDTO) {
        return scheduleMapper.toDTO(scheduleRepository.save(scheduleMapper.toEntity(scheduleDTO)));
    }
}
