package com.nmc.itschool.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity()
@Table(name = "test_result_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "test_result_id"))
public class TestResultEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_result_id", nullable = false)
    private Long id;

    @Column(name = "sort_oder")
    private String sortOder;

    @Column(name = "result")
    private String result;

    @Column(name = "type")
    private String type;

    @Column(name = "testId")
    private Long testId;

}


