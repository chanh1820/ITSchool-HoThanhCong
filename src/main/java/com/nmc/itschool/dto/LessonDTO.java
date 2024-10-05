package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class LessonDTO {

    private Integer id;

    private String lessonCode;

    private String lessonName;

    private String description;

    private String slug;

    private String imageUrl;

    private String pdfUrl;

    private String collectionPrefix;

    private String collectionParentPrefix;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean deleteFlag;
}