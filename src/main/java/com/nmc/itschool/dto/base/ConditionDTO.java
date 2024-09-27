package com.nmc.itschool.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConditionDTO {

    private Boolean parentheses = false;
    private String logicalOperator;
    private String field;
    private String operator;
    private Object value; // Could be String, Number, List<String>, List<Number>, or null
    private List<ConditionDTO> items;

}