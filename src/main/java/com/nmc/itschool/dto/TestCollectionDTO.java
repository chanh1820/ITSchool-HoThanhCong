package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TestCollectionDTO {

    private Long id;

    private String testCollectionUUID;

    private String testCollectionName;

    private String description;

    private String slug;

    private String thumbnailFile;

    private String collectionPrefix;

    private String collectionParentPrefix;

    private Integer numberChooseTest;

    private Integer numberWriteTest;

    private Boolean publicFlag;

    private String author;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean deleteFlag;

    private Integer maxPoint;

    private Integer minuteTime;
}

