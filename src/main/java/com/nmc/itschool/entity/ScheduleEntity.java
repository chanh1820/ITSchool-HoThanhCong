package com.nmc.itschool.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity()
@Table(name = "schedule_tbl")
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "schedule_id"))
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id", nullable = false)
    private Integer id;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "morning_hour")
    private String morningHour;

    @Column(name = "noon_hour")
    private String noonHour;

    @Column(name = "night_hour")
    private String nightHour;

    @Column(name = "medicine_list")
    private String medicineList;

    @Column(name = "note")
    private String note;

    @Column(name = "name")
    private String name;

    @Column(name = "account_id")
    private Integer accountId;
}
