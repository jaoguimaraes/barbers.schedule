package com.barbers.schedule.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PUBLIC)
public class CustomerDTO implements Serializable {

    private Long id;
    private String name;
    private AddressDTO address;

}
