package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class QuickQuizDTO {

    private Long id;

    private String userName;

    private String randomId;

    private String title;

    private String question;

    private String imageContentFile;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String result;

    private boolean isPicked;
}
