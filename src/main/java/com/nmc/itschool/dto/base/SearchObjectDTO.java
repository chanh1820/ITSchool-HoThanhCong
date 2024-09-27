package com.nmc.itschool.dto.base;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchObjectDTO {

    private int pageIndex;
    private int pageSize;
    private int limit;
    private List<FilterDTO> filters;

}
