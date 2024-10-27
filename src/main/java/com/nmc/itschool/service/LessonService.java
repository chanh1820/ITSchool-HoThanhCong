package com.nmc.itschool.service;

import com.nmc.itschool.dto.SubjectCollectionParentDTO;
import com.nmc.itschool.dto.LessonDTO;

import java.util.List;

public interface LessonService {

    public LessonDTO save(LessonDTO lessonDTO);
    public LessonDTO findBySlug(String slug);
    public List<LessonDTO> getAll(int limit);

    List<LessonDTO> findByPrefix(String prefix);
}
