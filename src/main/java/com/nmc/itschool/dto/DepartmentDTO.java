package com.nmc.itschool.dto;

import com.demo.crudemployee.dto.base.AuditDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO extends AuditDTO<UUID> {

    private UUID id;

    private String departmentCode;

    private String departmentName;

    private String description;

}
