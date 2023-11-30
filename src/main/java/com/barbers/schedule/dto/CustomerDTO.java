package com.barbers.schedule.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
