package com.nmc.itschool.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "subject_collection_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "subject_collection_id"))
public class SubjectCollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_collection_id", nullable = false)
    private Long id;

    @Column(name = "subject_collection_code")
    private String subjectCollectionCode;

    @Column(name = "subject_collection_name")
    private String subjectCollectionName;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    @ManyToOne
    @JoinColumn(name = "subject_collection_parent_id", referencedColumnName = "subject_collection_parent_id")
    @JsonIgnore
    private SubjectCollectionParentEntity subjectCollectionParentEntity;
}

