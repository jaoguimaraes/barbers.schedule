package com.barbers.schedule.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_employeeServices")
@Table(name = "tb_employeeServices")
@Getter
@Setter
public class EmployeeServices extends Services {

    private Integer employeeId;
    private Integer serviceId;
}
