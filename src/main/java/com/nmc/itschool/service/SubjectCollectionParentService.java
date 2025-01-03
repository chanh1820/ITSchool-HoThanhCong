package com.nmc.itschool.service;

import com.nmc.itschool.dto.SubjectCollectionParentDTO;

import java.util.List;

public interface SubjectCollectionParentService {

    public List<SubjectCollectionParentDTO> getAllByType(String type);
    public List<SubjectCollectionParentDTO> getAllInHome();

}
