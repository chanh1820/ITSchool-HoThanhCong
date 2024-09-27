package com.nmc.itschool.dto;

import com.demo.crudemployee.dto.base.AuditDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO extends AuditDTO<UUID> {

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Date hiredDate;

    private DepartmentDTO departmentDTO;

    private JobDTO jobDTO;

}
