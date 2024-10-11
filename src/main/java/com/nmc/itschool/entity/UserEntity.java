package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@Table(name = "user_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true, length = 45)
    private String userName;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "day_of_birth", length = 20)
    private String dayOfBirth;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "class_room", length = 20)
    private String classRoom;
}
