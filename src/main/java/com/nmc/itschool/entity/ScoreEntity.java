package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "score_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "score_id"))
public class ScoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "randomId", nullable = false, unique = true, length = 45)
    private String randomId;

    @Column(name = "user_name", nullable = false, length = 45)
    private String userName;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "class_room", length = 20)
    private String classRoom;

    @Column(name = "test_code")
    private String testCode;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "slug")
    private String slug;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "minute_time")
    private Integer minuteTime;

    @Column(name = "point")
    private float point;
}
