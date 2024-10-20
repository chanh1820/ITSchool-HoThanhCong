package com.nmc.itschool.service;

import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.NoteDTO;
import com.nmc.itschool.dto.ScoreDTO;
import com.nmc.itschool.dto.UserDoTestDTO;

import java.util.List;

public interface NoteService {

    List<NoteDTO> getByUserName(String userName);
    NoteDTO findByRandomId(String randomId);
    NoteDTO save(NoteDTO noteDTO);

    String init(String userName);

}
