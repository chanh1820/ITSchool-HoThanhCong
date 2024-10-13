package com.nmc.itschool.service;

import com.nmc.itschool.dto.ScoreDTO;
import com.nmc.itschool.dto.UserDTO;
import com.nmc.itschool.dto.UserDoTestDTO;

public interface ScoreService {

    ScoreDTO pushAnswerTest(UserDoTestDTO userDoTestDTO);
    ScoreDTO findByRandomId(String randomId);
}
