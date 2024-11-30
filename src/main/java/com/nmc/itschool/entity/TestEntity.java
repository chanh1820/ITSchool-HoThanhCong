package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "test_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "test_id"))
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", nullable = false)
    private Long id;

    @Column(name = "test_code")
    private String testCode;

    @Column(name = "test_collection_uuid")
    private String testCollectionUUID;

    @Column(name = "test_name")
    private String testName;

    @Column(name = "description")
    private String description;

    @Column(name = "slug")
    private String slug;

    @Column(name = "thumbnail_file")
    private String thumbnailFile;

    @Column(name = "pdf_file")
    private String pdfFile;

    @Column(name = "json_list_item_question")
    private String jsonListItemQuestion;

    @Column(name = "max_point")
    private Integer maxPoint;

    @Column(name = "minute_time")
    private Integer minuteTime;

    @Column(name = "collection_prefix")
    private String collectionPrefix;

    @Column(name = "collection_parent_prefix")
    private String collectionParentPrefix;

    @Column(name = "number_choose_test")
    private Integer numberChooseTest;

    @Column(name = "number_write_test")
    private Integer numberWriteTest;

    @Column(name = "public_flag")
    private Boolean publicFlag;

    @Column(name = "author")
    private String author;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "delete_flag")
    private Boolean deleteFlag;

    @ManyToOne
    @JoinColumn(name = "test_collection_id")
    private TestCollectionEntity testCollection;
}

