package com.nmc.itschool.controller.rest;

import com.nmc.itschool.dto.QuickQuizDTO;
import com.nmc.itschool.dto.base.BaseResponse;
import com.nmc.itschool.service.NoteService;
import com.nmc.itschool.service.QuickQuizService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quick-quiz")
public class QuickQuizRestController {
    private Logger logger = LoggerFactory.getLogger(QuickQuizRestController.class);
    @Autowired
    private QuickQuizService quickQuizService;

    @PostMapping("/save")
    public BaseResponse<QuickQuizDTO> saveNote(@RequestBody QuickQuizDTO quickQuizDTO) {
        logger.info("Start saveNote with {}", quickQuizDTO);
        BaseResponse<QuickQuizDTO> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(200);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        quickQuizDTO.setUserName(userName);
        baseResponse.setData(quickQuizService.save(quickQuizDTO));
        logger.info("End saveNote");
        return baseResponse;
    }

    @GetMapping("/init")
    public BaseResponse<String> initQuickQuiz() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        logger.info("Start initQuickQuiz with {}", userName);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setData(quickQuizService.init(userName));

        logger.info("End initQuickQuiz");
        return baseResponse;
    }
}
