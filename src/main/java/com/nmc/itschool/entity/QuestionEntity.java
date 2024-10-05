package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "question_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "question_id"))
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", nullable = false)
    private Long id;

    @Column(name = "random_id")
    private String randomId;

    @Column(name = "answer_a")
    private String answerA;

    @Column(name = "answer_b")
    private String answerB;

    @Column(name = "answer_c")
    private String answerC;

    @Column(name = "answer_d")
    private String answerD;

    @Column(name = "result")
    private String result;

    @Column(name = "test_code")
    private String testCode;
}
