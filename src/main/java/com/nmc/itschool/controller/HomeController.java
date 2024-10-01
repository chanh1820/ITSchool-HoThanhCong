package com.nmc.itschool.controller;

import com.nmc.itschool.dto.LessonCollectionParentDTO;
import com.nmc.itschool.service.LessonCollectionParentService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    @Autowired
    LessonCollectionParentService lessonCollectionParentService;

    @GetMapping("/home")
    public String homePage(Model model) {
        log.info("start homePage");

        List<LessonCollectionParentDTO> lessonCollectionParentDTOS = lessonCollectionParentService.getAll();
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(lessonCollectionParentDTOS));
        model.addAttribute("lessonCollectionParentDTOS", lessonCollectionParentDTOS);

        log.info("end homePage");

        return "home";  // Trả về tệp home.html trong thư mục templates
    }
}