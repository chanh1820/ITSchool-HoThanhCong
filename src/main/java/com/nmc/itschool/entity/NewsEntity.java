package com.nmc.itschool.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity()
@Table(name = "news_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "news_id"))
public class NewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id", nullable = false)
    private Long id;

    @Column(name = "news_code")
    private String newsCode;

    @Column(name = "news_name")
    private String newsName;

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

