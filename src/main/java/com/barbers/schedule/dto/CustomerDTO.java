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
public class CustomerDTO implements Serializable {

    private Long id;
    private String name;
    private AddressDTO address;

}
