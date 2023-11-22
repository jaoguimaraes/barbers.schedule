package com.barbers.schedule.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "tb_employee")
@Table(name = "tb_employee")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Employee {

    @Id
    private Integer id;
    private String name;
    private Date work_start_date;
    private Date work_end_date;
    private Date lunch_start_date;
    private Date lunch_end_date;
    private Boolean active;
}
