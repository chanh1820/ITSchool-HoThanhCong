package com.nmc.itschool.controller.rest;

import com.nmc.itschool.service.LessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsRestController {
    private Logger logger = LoggerFactory.getLogger(NewsRestController.class);
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
