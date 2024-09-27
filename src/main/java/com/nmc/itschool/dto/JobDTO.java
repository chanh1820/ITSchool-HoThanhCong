package com.nmc.itschool.dto;

import com.demo.crudemployee.dto.base.AuditDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class JobDTO extends AuditDTO<UUID> {

    private String jobCode;

    private String jobName;

    private String description;

    private BigDecimal minSalary;

    private BigDecimal maxSalary;

}
