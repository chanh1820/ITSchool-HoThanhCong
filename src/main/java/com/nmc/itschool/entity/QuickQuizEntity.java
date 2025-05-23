package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@Table(name = "quick_quiz_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "quick_quiz_id"))
public class QuickQuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "random_id")
    private String randomId;

    @Column(name = "title", length = 13383)
    private String title;

    @Column(name = "question", length = 13383)
    private String question;

    @Column(name = "image_content_file")
    private String imageContentFile;

    @Column(name = "answer_a", length = 13383)
    private String answerA;

    @Column(name = "answer_b", length = 13383)
    private String answerB;

    @Column(name = "answer_c", length = 13383)
    private String answerC;

    @Column(name = "answer_d", length = 13383)
    private String answerD;

    @Column(name = "result")
    private String result;

    @Column(name = "is_picked", nullable = false)
    private boolean isPicked = false;
}
