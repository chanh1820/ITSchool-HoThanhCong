package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ScoreDTO {

    private Long id;

    private String randomId;

    private String userName;

    private String fullName;

    private String classRoom;

    private String testCode;

    private String testName;

    private String slug;

    private LocalDateTime createdDate;

    private Integer minuteTime;

    private float point;

}
