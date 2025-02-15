package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class RateDTO {
    private Long id;

    private Long userId;

    private String userName;

    private String fullName;

    private Integer rateValue;

    private String feedback;
}
