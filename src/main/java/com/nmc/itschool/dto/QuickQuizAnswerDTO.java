package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuickQuizAnswerDTO {

    private String userName;

    private String fullName;

    private String randomId;

    private String answer;
}
