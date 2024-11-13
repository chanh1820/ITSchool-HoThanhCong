package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QuickQuizLogDTO {

    private Long id;

    private String userName;

    private String fullName;

    private String randomId;

    private String title;

    private String question;

    private String answer;

    private Integer answerNumberTrue;

    private boolean isCorrect;

    private LocalDateTime createDate;
}
