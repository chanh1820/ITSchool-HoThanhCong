package com.nmc.itschool.controller.rest;

import com.nmc.itschool.dto.*;
import com.nmc.itschool.dto.base.BaseResponse;
import com.nmc.itschool.security.CustomUserDetails;
import com.nmc.itschool.service.ScoreService;
import com.nmc.itschool.service.SubjectCollectionParentService;
import com.nmc.itschool.service.TestService;
import com.nmc.itschool.service.UserDoTestService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestRestController {
    private Logger logger = LoggerFactory.getLogger(TestRestController.class);

    @Autowired
    private TestService testService;

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserDoTestService userDoTestService;
    @PostMapping("/save-result")
    public BaseResponse saveResultTestItem(@RequestBody TestResultSaveDTO testResultSaveDTO) {
        logger.info("Start saveResultTestItem {}", testResultSaveDTO);

        List<TestResultDTO> result = testService.saveResultItem(testResultSaveDTO);
        TestDTO testDTO = testService.findById(result.get(0).getTestId());
        logger.info("End saveResultTestItem with result {}", ObjectMapperUtil.writeValueAsString(result));
        logger.info("End saveResultTestItem with testDTO {}", ObjectMapperUtil.writeValueAsString(testDTO));
        return BaseResponse.success(testDTO);
    }

    @PostMapping("/save-user-do-test")
    public BaseResponse saveUserDoTest(@RequestBody UserDoTestDTO userDoTestDTO) {
        logger.info("Start saveUserDoTest {}", userDoTestDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            log.info("userDetails.getUsername() {}", userDetails.getUsername());
            userDoTestDTO.setUserName(userDetails.getUsername());
        }

        UserDoTestDTO result = userDoTestService.save(userDoTestDTO);

        logger.info("End saveUserDoTest with data {}", ObjectMapperUtil.writeValueAsString(result));
        return BaseResponse.success(result);
    }

    @PostMapping("/update-realtime-user-do-test")
    public BaseResponse updateRealTimeUserDoTest(@RequestBody UserDoTestDTO userDoTestDTO) {
        logger.info("Start updateRealTimeUserDoTest {}", userDoTestDTO);

        UserDoTestDTO result = userDoTestService.updateReadTime(userDoTestDTO);

        logger.info("End updateRealTimeUserDoTest with data {}", ObjectMapperUtil.writeValueAsString(result));
        return BaseResponse.success(result);
    }

    @PostMapping("/push-answer-test")
    public BaseResponse pushAnswerTest(@RequestBody UserDoTestDTO userDoTestDTO) {
        logger.info("Start pushAnswerTest {}", userDoTestDTO);

        ScoreDTO result = scoreService.pushAnswerTest(userDoTestDTO);

        logger.info("End pushAnswerTest with data {}", ObjectMapperUtil.writeValueAsString(result));
        return BaseResponse.success(result);
    }


}
