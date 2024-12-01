package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDoTestDTO {

    private Long id;

    private String userName;

    private String testSlug;

    private String jsonListItemQuestion;

    private Integer minuteTime;

    private Boolean deleteFlag;

}
