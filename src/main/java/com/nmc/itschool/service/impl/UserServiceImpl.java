package com.nmc.itschool.service.impl;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.TestDTO;
import com.nmc.itschool.dto.UserDTO;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import com.nmc.itschool.entity.TestEntity;
import com.nmc.itschool.entity.UserEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.TestMapper;
import com.nmc.itschool.mapper.UserMapper;
import com.nmc.itschool.repository.SubjectCollectionParentRepository;
import com.nmc.itschool.repository.SubjectCollectionRepository;
import com.nmc.itschool.repository.TestRepository;
import com.nmc.itschool.repository.UserRepository;
import com.nmc.itschool.service.TestService;
import com.nmc.itschool.service.UserService;
import com.nmc.itschool.util.ObjectMapperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;


    @Override
    public UserDTO save(UserDTO userDTO) {
        Optional<UserEntity> otp = userRepository.findByUserName(userDTO.getUserName());
        if(otp.isPresent()){
            return null;
        }

        UserEntity result = userRepository.save(userMapper.toEntity(userDTO));
        return userMapper.toDTO(result);
    }
}
