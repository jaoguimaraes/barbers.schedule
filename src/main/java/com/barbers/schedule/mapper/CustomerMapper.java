package com.barbers.schedule.mapper;

import com.barbers.schedule.domain.model.Address;
import com.barbers.schedule.domain.model.Customer;
import com.barbers.schedule.dto.AddressDTO;
import com.barbers.schedule.dto.CustomerDTO;

import java.util.Objects;

public class CustomerMapper {

    public static Customer map(final CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setAddress(map(customerDTO.getAddress()));
        return customer;
    }

    public static Address map(final AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        address.setNumber(addressDTO.getNumber());
        return address;
    }

    public static CustomerDTO map(final Customer customer) {
        CustomerDTO.CustomerDTOBuilder customerDTOBuilder = CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName());
        if (Objects.nonNull(customer.getAddress())) {
            customerDTOBuilder.address(map(customer.getAddress()));
        }
        return customerDTOBuilder.build();
    }

    public static AddressDTO map(final Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }

}
