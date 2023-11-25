package com.barbers.schedule.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ToString
public class AddressDTO implements Serializable {

    private Long id;
    private String street;
    private String number;
    private String zipCode;
    private String city;
    private String state;
    private String country;

}
