package com.nmc.itschool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {

    private Long id;

    private String contentCode;

    private String contentName;

    private String description;

    private String slug;

    private String imageUrl;
}