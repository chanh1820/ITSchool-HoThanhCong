package com.nmc.itschool.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ScheduleDTO {

    private Long id;

    private Date startDate;

    private Date endDate;

    private String morningHour;

    private String noonHour;

    private String nightHour;

    private String medicineList;

    private List<MedicineDTO> medicineDTOS;

    private String note;

    private String name;

    private Integer accountId;
}
