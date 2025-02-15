package com.nmc.itschool.service;

import com.nmc.itschool.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RateService {
    RateDTO save(RateDTO rateDTO);

    List<RateDTO> getAll();
    Float getAvg();

}
