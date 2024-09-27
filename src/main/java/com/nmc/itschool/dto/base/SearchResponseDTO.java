package com.nmc.itschool.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponseDTO<T> {

    private Integer totalRecord;
    private Integer pageIndex = 0;
    private Integer pageSize = 10;
    private Integer limit = -1;
    private T data;

}
