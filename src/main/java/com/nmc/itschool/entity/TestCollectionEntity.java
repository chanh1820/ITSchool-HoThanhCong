package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "test_collection_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "test_id"))
public class TestCollectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_collection_id", nullable = false)
    private Long id;

    @Column(name = "test_collection_uuid")
    private String testCollectionUUID;


    @Column(name = "test_collection_name")
    private String testCollectionName;

    @Column(name = "description")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "thumbnail_file")
    private String thumbnailFile;

    @Column(name = "collection_prefix")
    private String collectionPrefix;

    @Column(name = "collection_parent_prefix")
    private String collectionParentPrefix;

    @Column(name = "public_flag")
    private Boolean publicFlag;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;
}

