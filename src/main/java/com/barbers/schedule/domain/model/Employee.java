package com.barbers.schedule.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "employee")
@Getter
@Setter
@EqualsAndHashCode
public class Employee {

    @Id
    private Long id;
    private String name;
    private Date workStartDate;
    private Date workEndDate;
    private Date lunchStartDate;
    private Date lunchEndDate;
    private Boolean active;

    @ManyToMany(mappedBy = "employees")
    Set<Customer> customers;

    @OneToMany(mappedBy = "employee")
    Set<Schedules> schedules;

    @ManyToMany
    @JoinTable(
            name = "employee_services",
            joinColumns = @JoinColumn(name = "service_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    Set<Services> services;
}
