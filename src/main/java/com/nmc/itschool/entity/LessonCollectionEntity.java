package com.nmc.itschool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "lesson_collection_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "lesson_collection_id"))
public class LessonCollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_collection_id", nullable = false)
    private Integer id;

    @Column(name = "lesson_collection_code")
    private String lessonCollectionCode;

    @Column(name = "lesson_collection_name")
    private String lessonCollectionName;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    @ManyToOne
    @JoinColumn(name = "lesson_collection_parent_id", referencedColumnName = "lesson_collection_parent_id")
    @JsonIgnore
    private LessonCollectionParentEntity lessonCollectionParentEntity;
}

