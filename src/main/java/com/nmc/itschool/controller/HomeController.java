package com.nmc.itschool.controller;

import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.security.CustomUserDetails;
import com.nmc.itschool.service.SubjectCollectionParentService;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.service.TestService;
import com.nmc.itschool.util.FileUtil;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    TestService testService;
    @GetMapping("/index")
    public String homePage(Model model) {
        log.info("start homePage");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            log.info("userInfo {}", userDetails.getFullName());
            model.addAttribute("userInfo", userDetails.getFullName());
        }else {
            model.addAttribute("userInfo", "null");
        }
        // get Data
        List<SubjectCollectionParentDTO> subjectCollectionParentDTOS = subjectCollectionParentService.getAll();
        List<LessonDTO> lessonDTOS = lessonService.getAll(15);
        List<TestDTO> testDTOS = testService.getAll(15);

        // add data
        log.info("data: {}", ObjectMapperUtil.writeValueAsString(subjectCollectionParentDTOS));
        log.info("lessonDTOS: {}", ObjectMapperUtil.writeValueAsString(lessonDTOS));
        model.addAttribute("pathFile", FileUtil.getPathResourceFile());
        model.addAttribute("subjectCollectionParentDTOS", subjectCollectionParentDTOS);
        model.addAttribute("lessonDTOS", lessonDTOS);
        model.addAttribute("testDTOS", testDTOS);

        log.info("end homePage");

        return "home/home";  // Trả về tệp home.html trong thư mục templates
    }
}