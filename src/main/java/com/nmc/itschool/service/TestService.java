package com.nmc.itschool.service;

import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.QuestionItemDTO;
import com.nmc.itschool.dto.TestDTO;

import java.util.List;
import java.util.Map;

public interface TestService {

    public TestDTO save(TestDTO testDTO);
    public TestDTO saveItem(TestDTO testDTO);
    public TestDTO findBySlug(String slug);
    public List<TestDTO> getAll(int limit);

}
