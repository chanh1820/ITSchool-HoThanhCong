package com.nmc.itschool.controller.rest;

import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.dto.base.BaseResponse;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.service.SubjectCollectionParentService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectRestController {
    private Logger logger = LoggerFactory.getLogger(SubjectRestController.class);

    @Autowired
    private SubjectCollectionParentService subjectCollectionParentService;

    @GetMapping("/get-all")
    public List<SubjectCollectionParentDTO> getAllSubject() {
        logger.info("Start getAllSubject");
        List<SubjectCollectionParentDTO> result = subjectCollectionParentService.getAll();
        logger.info("End getAllSubject with data {}", ObjectMapperUtil.writeValueAsString(result));
        return result;
    }


}
