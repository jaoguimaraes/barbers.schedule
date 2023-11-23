package com.barbers.schedule.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@EqualsAndHashCode
public class Schedules {

    @Id
    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String clientName;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
