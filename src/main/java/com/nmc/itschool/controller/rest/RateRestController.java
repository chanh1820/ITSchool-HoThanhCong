package com.nmc.itschool.controller.rest;

import com.nmc.itschool.dto.QuickQuizAnswerDTO;
import com.nmc.itschool.dto.QuickQuizDTO;
import com.nmc.itschool.dto.RateDTO;
import com.nmc.itschool.dto.base.BaseResponse;
import com.nmc.itschool.security.CustomUserDetails;
import com.nmc.itschool.service.QuickQuizService;
import com.nmc.itschool.service.RateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rate")
public class RateRestController {
    private Logger logger = LoggerFactory.getLogger(RateRestController.class);
    @Autowired
    private RateService rateService;

    @PostMapping("/submit")
    public BaseResponse<RateDTO> submitRate(@RequestBody RateDTO rateDTO) {
        logger.info("Start submitRate with {}", rateDTO);
        BaseResponse<RateDTO> baseResponse = new BaseResponse<>();
        baseResponse.setStatus(200);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        String fullName = userDetails.getFullName();
        rateDTO.setUserName(userName);
        rateDTO.setFullName(fullName);
        rateDTO.setUserId(1L);

        baseResponse.setData(rateService.save(rateDTO));

        logger.info("End submitRate");
        return baseResponse;
    }
}
