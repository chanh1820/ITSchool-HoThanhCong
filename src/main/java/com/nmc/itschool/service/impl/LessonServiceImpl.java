package com.nmc.itschool.service.impl;

import com.nmc.itschool.constant.MessageEnum;
import com.nmc.itschool.dto.LessonDTO;
import com.nmc.itschool.entity.SubjectCollectionEntity;
import com.nmc.itschool.entity.SubjectCollectionParentEntity;
import com.nmc.itschool.entity.LessonEntity;
import com.nmc.itschool.exceptions.AppException;
import com.nmc.itschool.mapper.LessonMapper;
import com.nmc.itschool.repository.SubjectCollectionParentRepository;
import com.nmc.itschool.repository.SubjectCollectionRepository;
import com.nmc.itschool.repository.LessonRepository;
import com.nmc.itschool.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    SubjectCollectionParentRepository subjectCollectionParentRepository;

    @Autowired
    SubjectCollectionRepository subjectCollectionRepository;

    @Autowired
    LessonMapper lessonMapper;
    @Override
    public LessonDTO save(LessonDTO lessonDTO) {
        validateSaveLesson(lessonDTO);
        lessonDTO.setCreatedDate(LocalDateTime.now());
        lessonDTO.setUpdatedDate(LocalDateTime.now());
        lessonDTO.setDeleteFlag(false);
        LessonEntity result = lessonRepository.save(lessonMapper.toEntity(lessonDTO));
        return lessonMapper.toDTO(result);
    }

    @Override
    public LessonDTO findBySlug(String slug) {
        Optional<List<LessonEntity>> otp = lessonRepository.findBySlug(slug);
        if (otp.isPresent()){
            return lessonMapper.toDTO(otp.get().get(0));
        }else {
            return null;
        }
    }

    @Override
    public List<LessonDTO> getAll(int limit) {
        Optional<List<LessonEntity>> otp = lessonRepository.getAll(limit);
        if (otp.isPresent()){
            return lessonMapper.toDTOs(otp.get());
        }
        return null;
    }

    @Override
    public List<LessonDTO> findByPrefix(String prefix) {
        Optional<List<LessonEntity>> otp = lessonRepository.findByPrefix(prefix);
        if (otp.isPresent() && otp.get().size() != 0){
            return lessonMapper.toDTOs(otp.get());
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        lessonRepository.deleteById(id);
    }

    private void validateSaveLesson(LessonDTO lessonDTO) {
        Optional<SubjectCollectionParentEntity> optParentCollection = subjectCollectionParentRepository.getByPrefix(lessonDTO.getCollectionParentPrefix());
        if (!optParentCollection.isPresent()){
            throw new AppException(MessageEnum.ERR_PREFIX_PARENT_NOT_MATCH);
        }
        Optional<SubjectCollectionEntity> optChildCollection = subjectCollectionRepository.getByPrefix(lessonDTO.getCollectionPrefix());
        if (!optChildCollection.isPresent()){
            throw new AppException(MessageEnum.ERR_PREFIX_CHILD_NOT_MATCH);
        }
    }


}
