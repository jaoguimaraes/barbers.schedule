package com.barbers.schedule.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_customerEmployee")
@Table(name = "tb_customerEmployee")
@Getter
@Setter
public class CustomerEmployee extends Customer {

    private Integer customerId;
    private Integer employeeId;
}
