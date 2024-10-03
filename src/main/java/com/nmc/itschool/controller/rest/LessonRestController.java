package com.nmc.itschool.controller.rest;

import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.base.BaseResponse;
import com.nmc.itschool.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/lesson")
public class LessonRestController {
    private Logger logger = LoggerFactory.getLogger(LessonRestController.class);
    private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads";

    @Autowired
    private LessonService lessonService;

//    @PostMapping("/save")
//    public BaseResponse<LessonDTO> saveLesson(@RequestBody LessonDTO lessonDTO) {
//        logger.info("Start saveLesson with {}", lessonDTO);
//        BaseResponse<LessonDTO> baseResponse = new BaseResponse<>();
//        baseResponse.setData(lessonService.save(lessonDTO));
//        logger.info("End saveLesson");
//        return baseResponse;
//    }


}
