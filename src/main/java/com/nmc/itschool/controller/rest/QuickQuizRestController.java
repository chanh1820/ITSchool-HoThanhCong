package com.nmc.itschool.controller.rest;

import com.nmc.itschool.dto.QuickQuizAnswerDTO;
import com.nmc.itschool.dto.QuickQuizDTO;
import com.nmc.itschool.dto.base.BaseResponse;
import com.nmc.itschool.security.CustomUserDetails;
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

    @PostMapping("/enable-item")
    public BaseResponse<String> enableQuickQuiz(@RequestBody QuickQuizDTO quickQuizDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        logger.info("Start enableQuickQuiz with {}", userName);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setData(quickQuizService.enable(quickQuizDTO.getRandomId()));

        logger.info("End enableQuickQuiz");
        return baseResponse;
    }

    @PostMapping("/disable-item")
    public BaseResponse<String> disableQuickQuiz(@RequestBody QuickQuizDTO quickQuizDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        logger.info("Start disableQuickQuiz with {}", userName);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setData(quickQuizService.disable(quickQuizDTO.getRandomId()));

        logger.info("End disableQuickQuiz");
        return baseResponse;
    }

    @PostMapping("/submit-answer")
    public BaseResponse<Boolean> submitAnswer(@RequestBody QuickQuizAnswerDTO quickQuizAnswerDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        logger.info("Start submitAnswer with {}", customUserDetails);
        quickQuizAnswerDTO.setFullName(customUserDetails.getFullName());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setData(quickQuizService.submitAnswer(quickQuizAnswerDTO));

        logger.info("End submitAnswer");
        return baseResponse;
    }
}
