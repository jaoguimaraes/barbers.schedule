package com.barbers.schedule.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "tb_customerAddress")
@Table(name = "tb_customerAddress")
@Getter
@Setter
public class CustomerAddress extends Customer{

    private Integer id;
    private String street;
    private Integer number;
    private Integer zip_code;
    private String city;
    private String state;
    private String country;
}
