package com.nmc.itschool.controller;

import com.nmc.itschool.dto.QuickQuizDTO;
import com.nmc.itschool.dto.QuickQuizLogDTO;
import com.nmc.itschool.dto.QuickQuizRankDTO;
import com.nmc.itschool.dto.RateDTO;
import com.nmc.itschool.service.QuickQuizService;
import com.nmc.itschool.service.RateService;
import com.nmc.itschool.util.FileUtil;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/rate")
public class RateController {

    @Autowired
    RateService rateService;


    @GetMapping("/submit")
    public String submitRate(Model model) {
        log.info("start submitRate");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        log.info("end submitRate");

        return "rate/rate_submit";
    }

    @GetMapping("/history")
    public String rateHistory(Model model) {
        log.info("start rateHistory");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        List<RateDTO> rateDTOS = rateService.getAll();
        Float avgRate = rateService.getAvg();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            log.info(userDetails.getUsername());
            log.info(ObjectMapperUtil.writeValueAsString(rateDTOS));
            model.addAttribute("rateDTOS", rateDTOS);
            model.addAttribute("avgRate", avgRate);
            return "rate/rate_history";
        }else {
            return "user/user_login";
        }
    }
    @GetMapping("/thanks-you")
    public String thanksYou(Model model) {
        log.info("Go thanksYou");
        return "rate/rate_thanks_you";
    }
}