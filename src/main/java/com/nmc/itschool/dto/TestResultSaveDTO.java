package com.nmc.itschool.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestResultSaveDTO implements Serializable {

    private Long testId;

    private List<TestResultDTO> answerChooseList;

    private List<TestResultDTO> answerWriteList;

}


