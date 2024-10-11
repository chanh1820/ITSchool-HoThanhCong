package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String userName;

    private String password;

    private String fullName;

    private String dayOfBirth;

    private String gender;

    private String classRoom;
}
