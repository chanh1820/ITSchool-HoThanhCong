package com.nmc.itschool.controller.rest;

import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.NoteDTO;
import com.nmc.itschool.dto.base.BaseResponse;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/note")
public class NoteRestController {
    private Logger logger = LoggerFactory.getLogger(NoteRestController.class);
    @Autowired
    private NoteService noteService;

    @PostMapping("/save")
    public BaseResponse<NoteDTO> saveNote(@RequestBody NoteDTO noteDTO) {
        logger.info("Start saveNote with {}", noteDTO);
        BaseResponse<NoteDTO> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(200);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        noteDTO.setUserName(userName);
        baseResponse.setData(noteService.save(noteDTO));
        logger.info("End saveNote");
        return baseResponse;
    }

    @GetMapping("/init")
    public BaseResponse<String> initNote() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();

        logger.info("Start initNote with {}", userName);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(200);
        baseResponse.setData(noteService.init(userName));

        logger.info("End initNote");
        return baseResponse;
    }
}
