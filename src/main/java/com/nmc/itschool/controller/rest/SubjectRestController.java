package com.nmc.itschool.controller.rest;

import com.nmc.itschool.constant.DBConstant;
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

    @GetMapping("/get-lesson")
    public List<SubjectCollectionParentDTO> getAllLessonSubject() {
        logger.info("Start getAllLessonSubject");
        List<SubjectCollectionParentDTO> result = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_LESSON);
        logger.info("End getAllLessonSubject with data {}", ObjectMapperUtil.writeValueAsString(result));
        return result;
    }

    @GetMapping("/get-news")
    public List<SubjectCollectionParentDTO> getAllNewsSubjectBy() {
        logger.info("Start getAllNewsSubjectBy");
        List<SubjectCollectionParentDTO> result = subjectCollectionParentService.getAllByType(DBConstant.TYPE_COLLECTION_PARENT_NEWS);
        logger.info("End getAllNewsSubjectBy with data {}", ObjectMapperUtil.writeValueAsString(result));
        return result;
    }
}
