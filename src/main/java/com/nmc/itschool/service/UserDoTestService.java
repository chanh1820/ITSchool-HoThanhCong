package com.nmc.itschool.service;

import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.dto.UserDoTestDTO;

import java.util.List;

public interface UserDoTestService {
    UserDoTestDTO getByTestSlugAndUserName(String slug, String userName);

    UserDoTestDTO save(UserDoTestDTO userDoTestDTO);

    UserDoTestDTO updateReadTime(UserDoTestDTO userDoTestDTO);
}
