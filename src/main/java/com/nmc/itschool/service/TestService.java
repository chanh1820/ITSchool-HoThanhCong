package com.nmc.itschool.service;

import com.nmc.itschool.dto.*;

import java.util.List;
import java.util.Map;

public interface TestService {

    public TestDTO save(TestDTO testDTO);
    public TestDTO findById(Long id);
    public List<TestResultDTO> saveResultItem(TestResultSaveDTO testResultSaveDTO);
    public List<TestResultDTO> getResultItem(Long testId);
    public TestDTO findBySlug(String slug);
    public List<TestDTO> getAll(int limit);
    public List<TestDTO> getTestByCollectionUUID(String uuid);
    void deleteById(Long id);
    List<TestDTO> findByPrefix(String prefix);

    public TestCollectionDTO saveCollection(TestCollectionDTO testCollectionDTO);
    public List<TestCollectionDTO> getCollectionAll(int limit);
    public TestCollectionDTO getCollectionByUUID(String uuid);
    public TestCollectionDTO getCollectionBySlug(String slug);
    void deleteCollectionById(Long id);
}
