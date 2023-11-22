package com.barbers.schedule.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "tb_schedules")
@Table(name = "tb_schedules")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Schedules {

    @Id
    private Integer id;
    private String name;
    private String description;
    private Date start_date;
    private Date end_date;
    private Integer employeeId;
    private String clientName;
}
