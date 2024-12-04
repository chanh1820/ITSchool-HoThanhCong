package com.nmc.itschool.service.impl;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.NewsDTO;
import com.nmc.itschool.dto.NewsDTO;
import com.nmc.itschool.entity.NewsEntity;
import com.nmc.itschool.entity.NewsEntity;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.NewsMapper;
import com.nmc.itschool.repository.NewsRepository;
import com.nmc.itschool.repository.SubjectCollectionParentRepository;
import com.nmc.itschool.repository.SubjectCollectionRepository;
import com.nmc.itschool.service.LessonService;
import com.nmc.itschool.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    SubjectCollectionParentRepository subjectCollectionParentRepository;

    @Autowired
    SubjectCollectionRepository subjectCollectionRepository;

    @Autowired
    NewsMapper newsMapper;
    @Override
    public NewsDTO save(NewsDTO newsDTO) {
        validateSaveLesson(newsDTO);
        newsDTO.setCreatedDate(LocalDateTime.now());
        newsDTO.setUpdatedDate(LocalDateTime.now());
        newsDTO.setDeleteFlag(false);
        NewsEntity result = newsRepository.save(newsMapper.toEntity(newsDTO));
        return newsMapper.toDTO(result);
    }

    @Override
    public NewsDTO findBySlug(String slug) {
        Optional<List<NewsEntity>> otp = newsRepository.findBySlug(slug);
        if (otp.isPresent()){
            return newsMapper.toDTO(otp.get().get(0));
        }else {
            return null;
        }
    }

    @Override
    public List<NewsDTO> getAll(int limit) {
        Optional<List<NewsEntity>> otp = newsRepository.getAll(limit);
        if (otp.isPresent()){
            return newsMapper.toDTOs(otp.get());
        }
        return null;
    }

    @Override
    public List<NewsDTO> findByPrefix(String prefix) {
        Optional<List<NewsEntity>> otp = newsRepository.findByPrefix(prefix);
        if (otp.isPresent() && otp.get().size() != 0){
            return newsMapper.toDTOs(otp.get());
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    private void validateSaveLesson(NewsDTO newsDTO) {
        Optional<SubjectCollectionParentEntity> optParentCollection = subjectCollectionParentRepository.getByPrefix(newsDTO.getCollectionParentPrefix());
        if (!optParentCollection.isPresent()){
            throw new AppException(MessageEnum.ERR_PREFIX_PARENT_NOT_MATCH);
        }
        Optional<SubjectCollectionEntity> optChildCollection = subjectCollectionRepository.getByPrefix(newsDTO.getCollectionPrefix());
        if (!optChildCollection.isPresent()){
            throw new AppException(MessageEnum.ERR_PREFIX_CHILD_NOT_MATCH);
        }
    }


}
