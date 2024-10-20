package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class NoteDTO {

    private Long id;

    private String randomId;

    private String userName;

    private String title;

    private String htmlContent;

    private String version;

    private boolean deleteFlag;
}
