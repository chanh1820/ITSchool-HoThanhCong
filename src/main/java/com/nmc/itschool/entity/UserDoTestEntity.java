package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "user_do_test_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "user_do_test_id"))
public class UserDoTestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_do_test_id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 45)
    private String userName;

    @Column(name = "slug")
    private String slug;

    @Column(name = "json_list_item_question")
    private String jsonListItemQuestion;

    @Column(name = "minute_time")
    private Integer minuteTime;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;
}

