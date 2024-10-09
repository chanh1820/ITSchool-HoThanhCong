package com.nmc.itschool.controller.rest;

import com.nmc.itschool.dto.QuestionItemDTO;
import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.dto.base.BaseResponse;
import com.nmc.itschool.service.SubjectCollectionParentService;
import com.nmc.itschool.service.TestService;
import com.nmc.itschool.util.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestRestController {
    private Logger logger = LoggerFactory.getLogger(TestRestController.class);

    @Autowired
    private TestService testService;

    @PostMapping("/save-item")
    public BaseResponse saveTestItem(@RequestBody TestDTO testDTO) {
        logger.info("Start saveTestItem {}", testDTO);

        TestDTO result = testService.saveItem(testDTO);
        logger.info("End saveTestItem with data {}", ObjectMapperUtil.writeValueAsString(result));
        return BaseResponse.success(result);
    }


}
