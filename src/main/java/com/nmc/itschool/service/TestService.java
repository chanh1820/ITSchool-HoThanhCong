package com.nmc.itschool.service;

import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.TestDTO;

import java.util.List;

public interface TestService {

    public TestDTO save(TestDTO testDTO);
    public TestDTO findBySlug(String slug);
    public List<TestDTO> getAll();

}
