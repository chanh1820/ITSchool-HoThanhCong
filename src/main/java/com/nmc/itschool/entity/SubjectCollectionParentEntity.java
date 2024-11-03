package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity()
@Table(name = "subject_collection_parent_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "subject_collection_parent_id"))
public class SubjectCollectionParentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_collection_parent_id", nullable = false)
    private Long id;

    @Column(name = "subject_collection_parent_code")
    private String subjectCollectionParentCode;

    @Column(name = "subject_collection_parent_name")
    private String subjectCollectionParentName;

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

    @OneToMany(mappedBy = "subjectCollectionParentEntity")
    private List<SubjectCollectionEntity> subjectCollectionEntities;
}

