package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TestDTO {

    private Long id;

    private String testCode;

    private String testName;

    private String description;

    private String slug;

    private String thumbnailFile;

    private String pdfFile;

    private String collectionPrefix;

    private String collectionParentPrefix;

    private Integer numberChooseTest;

    private Integer numberWriteTest;

    private Boolean publicFlag;

    private String author;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean deleteFlag;
}

