package com.nmc.itschool.service;

import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.QuestionItemDTO;
import com.nmc.itschool.dto.TestCollectionDTO;
import com.nmc.itschool.dto.TestDTO;

import java.util.List;
import java.util.Map;

public interface TestService {

    public TestDTO save(TestDTO testDTO);
    public TestDTO saveItem(TestDTO testDTO);
    public TestDTO findBySlug(String slug);
    public List<TestDTO> getAll(int limit);
    public List<TestDTO> getTestByCollectionUUID(String uuid);
    void deleteById(Long id);
    List<TestDTO> findByPrefix(String prefix);

    public TestCollectionDTO saveCollection(TestCollectionDTO testCollectionDTO);
    public List<TestCollectionDTO> getCollectionAll(int limit);
    public TestCollectionDTO getCollectionByUUID(String uuid);
}
