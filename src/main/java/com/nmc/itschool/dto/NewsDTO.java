package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class NewsDTO {
    private Long id;

    private String newsCode;

    private String newsName;

    private String description;

    private String slug;

    private String imageUrl;

    private String pdfUrl;

    private String videoId;

    private String collectionPrefix;

    private String collectionParentPrefix;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private Boolean deleteFlag;
}

