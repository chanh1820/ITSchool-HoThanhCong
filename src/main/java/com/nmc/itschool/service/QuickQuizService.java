package com.nmc.itschool.service;

import com.nmc.itschool.dto.NoteDTO;
import com.nmc.itschool.dto.QuickQuizAnswerDTO;
import com.nmc.itschool.dto.QuickQuizDTO;
import com.nmc.itschool.dto.QuickQuizLogDTO;

import java.util.List;

public interface QuickQuizService {

    List<QuickQuizDTO> getByUserName(String userName);

    QuickQuizDTO getPicked();

    QuickQuizDTO findByRandomId(String randomId);

    QuickQuizDTO save(QuickQuizDTO quickQuizDTO);

    String init(String userName);

    QuickQuizDTO enable(String randomId);

    boolean submitAnswer(QuickQuizAnswerDTO quickQuizAnswerDTO);

    List<QuickQuizLogDTO> findQuickQuizLogs(String randomId);
}
