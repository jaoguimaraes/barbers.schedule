package com.barbers.schedule.service;

import com.barbers.schedule.domain.model.Address;
import com.barbers.schedule.domain.model.Customer;
import com.barbers.schedule.dto.AddressDTO;
import com.barbers.schedule.dto.CustomerDTO;
import com.barbers.schedule.repository.CustomerRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDTO createCustomer(final @NonNull CustomerDTO customer) {
        try {
            if (Objects.isNull(customer.getAddress())) {
                throw new IllegalArgumentException(String.format("Address is null for customer %d", customer.getId()));
            }
            Customer save = customerRepository.save(map(customer));
            return map(save);
        } catch (Exception e) {
            log.error("Failed to create customer", e);
            throw e;
        }
    }

    public CustomerDTO findCustomerById(final Long id) {
        try {
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Customer not found: %d", id)));
            return map(customer);
        } catch (Exception e) {
            log.error("Failed to find customer", e);
            throw e;
        }
    }

    private Customer map(final CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setAddress(map(customerDTO.getAddress()));
        return customer;
    }

    private Address map(final AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());
        address.setNumber(addressDTO.getNumber());
        return address;
    }

    private CustomerDTO map(final Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .address(map(customer.getAddress()))
                .build();
    }

    private AddressDTO map(final Address address) {
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
