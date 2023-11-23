package com.barbers.schedule.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String number;
    private String zipCode;
    private String city;
    private String state;
    private String country;

    @JsonIgnore
    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Customer customer;
}
