//package com.nmc.itschool.entity;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity()
//@Table(name = "test_tbl")
//@Getter
//@Setter
//@NoArgsConstructor
//@AttributeOverride(name = "id", column = @Column(name = "test_id"))
//public class TestGroupEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "test_id", nullable = false)
//    private Integer id;
//
//    @Column(name = "test_code")
//    private String testCode;
//
//    @Column(name = "test_name")
//    private String testName;
//
//    @Column(name = "description")
//    private String description;
//
//    @Column(name = "slug")
//    private String slug;
//
//    @Column(name = "image_url")
//    private String thumbnailFile;
//
//    @Column(name = "pdf_url")
//    private String pdfFile;
//
//    @Column(name = "collection_prefix")
//    private String collectionPrefix;
//
//    @Column(name = "collection_parent_prefix")
//    private String collectionParentPrefix;
//
//    @Column(name = "public_flag")
//    private Boolean publicFlag;
//
//    @Column(name = "created_date")
//    private LocalDateTime createdDate;
//
//    @Column(name = "updated_date")
//    private LocalDateTime updatedDate;
//
//    @Column(name = "delete_flag")
//    private Boolean deleteFlag;
//}
//
