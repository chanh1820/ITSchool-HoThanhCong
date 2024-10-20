package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "note_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "note_id"))
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "randomId", nullable = false, unique = true, length = 45)
    private String randomId;

    @Column(name = "user_name", nullable = false, length = 45)
    private String userName;

    @Column(name = "title", length = 13383)
    private String title;

    @Column(name = "html_content", length = 999)
    private String htmlContent;

    @Column(name = "version", length = 45)
    private String version;

    @Column(name = "delete_flag")
    private boolean deleteFlag;
}
