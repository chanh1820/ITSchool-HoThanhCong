package com.nmc.itschool.service;

import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.dto.NewsDTO;

import java.util.List;

public interface NewsService {

    public NewsDTO save(NewsDTO newsDTO);
    public NewsDTO findBySlug(String slug);
    public List<NewsDTO> getAll(int limit);
    List<NewsDTO> findByPrefix(String prefix);
    public void deleteById(Long id);
}
