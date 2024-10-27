package com.nmc.itschool.service;

import com.nmc.itschool.dto.NoteDTO;
import com.nmc.itschool.dto.QuickQuizDTO;

import java.util.List;

public interface QuickQuizService {

    List<QuickQuizDTO> getByUserName(String userName);
    QuickQuizDTO findByRandomId(String randomId);
    QuickQuizDTO save(QuickQuizDTO quickQuizDTO);
    String init(String userName);

}
