package com.nmc.itschool.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterDTO {

    private String searchText;
    private List<ConditionDTO> conditions;
    private List<SortDTO> sorts;

}