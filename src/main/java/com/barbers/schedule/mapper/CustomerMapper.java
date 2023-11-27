package com.barbers.schedule.mapper;

import com.barbers.schedule.domain.model.Customer;
import com.barbers.schedule.dto.CustomerDTO;

import java.util.Objects;

public class CustomerMapper {

    public static Customer map(final CustomerDTO customerDTO) {
        return Customer.builder()
                .id(customerDTO.getId())
                .name(customerDTO.getName())
                .address(AddressMapper.map(customerDTO.getAddress()))
                .build();
    }

    public static CustomerDTO map(final Customer customer) {
        CustomerDTO.CustomerDTOBuilder customerDTOBuilder = CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName());
        if (Objects.nonNull(customer.getAddress())) {
            customerDTOBuilder.address(AddressMapper.map(customer.getAddress()));
        }
        return customerDTOBuilder.build();
    }

}
