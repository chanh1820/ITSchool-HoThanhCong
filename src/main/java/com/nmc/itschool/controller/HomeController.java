package com.nmc.itschool.controller;

import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.service.SubjectCollectionParentService;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.util.FileUtil;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/home")
public class HomeController {
    @Autowired
    SubjectCollectionParentService subjectCollectionParentService;

    @Autowired
    LessonService lessonService;

    @GetMapping("/index")
    public String homePage(Model model) {
        log.info("start homePage");

        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS = subjectCollectionParentService.getAll();
        List<LessonDTO> lessonDTOS = lessonService.getAll();

        // add data
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
        log.info("lessonDTOS: {}", ObjectMapperUtil.writeValueAsString(lessonDTOS));
        model.addAttribute("pathFile", FileUtil.getPathUploadFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);
        model.addAttribute("lessonDTOS", lessonDTOS);

        log.info("end homePage");

        return "home/home";  // Trả về tệp home.html trong thư mục templates
    }
}