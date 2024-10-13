package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity()
@Table(name = "lesson_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "lesson_id"))
public class LessonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id", nullable = false)
    private Long id;

    @Column(name = "lesson_code")
    private String lessonCode;

    @Column(name = "lesson_name")
    private String lessonName;

    @Column(name = "description")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "pdf_url")
    private String pdfUrl;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "collection_prefix")
    private String collectionPrefix;

    @Column(name = "collection_parent_prefix")
    private String collectionParentPrefix;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;
}

