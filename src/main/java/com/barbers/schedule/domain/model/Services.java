package com.barbers.schedule.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "services")
@Getter
@Setter
@EqualsAndHashCode
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal coast;

    @ManyToMany(mappedBy = "services")
    Set<Employee> employees;
}
