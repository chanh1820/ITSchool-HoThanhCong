package com.nmc.itschool.service;

import com.nmc.itschool.dto.LessonCollectionParentDTO;
import com.nmc.itschool.dto.LessonDTO;

import java.util.List;

public interface LessonService {

    public LessonDTO save(LessonDTO lessonDTO);
    public List<LessonDTO> getAll();

}
