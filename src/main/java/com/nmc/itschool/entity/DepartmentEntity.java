package com.nmc.itschool.entity;

import com.demo.crudemployee.entity.base.BaseAudit;
import com.demo.crudemployee.entity.base.BaseReadonlyEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "department")
public class DepartmentEntity extends BaseAudit implements BaseReadonlyEntity<UUID> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code")
    private String departmentCode;

    @Column(name = "name")
    private String departmentName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private boolean isDeleted;

}
