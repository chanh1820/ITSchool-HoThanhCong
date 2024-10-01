package com.nmc.itschool.service;

import com.nmc.itschool.dto.ScheduleDTO;
import com.nmc.itschool.sco.ScheduleSCO;

import java.util.List;

public interface ScheduleService {

    public List<ScheduleDTO> getAllByAccountId(ScheduleSCO scheduleSCO);
    public ScheduleDTO save(ScheduleDTO scheduleDTO);


}
