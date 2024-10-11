package com.nmc.itschool.controller.rest;

import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.dto.UserDTO;
import com.nmc.itschool.dto.base.BaseResponse;
import com.nmc.itschool.service.TestService;
import com.nmc.itschool.service.UserService;
import com.nmc.itschool.util.ObjectMapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/process_register")
    public String processRegister(UserDTO userDTO) {
        logger.info("Start processRegister {}", userDTO);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);
        userService.save(userDTO);
        logger.info("End processRegister {}", userDTO);

        return "register_success";
    }


}
