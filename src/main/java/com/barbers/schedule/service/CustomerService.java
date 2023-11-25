package com.barbers.schedule.service;

import com.barbers.schedule.domain.model.Customer;
import com.barbers.schedule.dto.CustomerDTO;
import com.barbers.schedule.mapper.CustomerMapper;
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
            Customer save = customerRepository.save(CustomerMapper.map(customer));
            return CustomerMapper.map(save);
        } catch (Exception e) {
            log.error("Failed to create customer", e);
            throw e;
        }
    }

    public CustomerDTO findCustomerById(final Long id) {
        try {
            Customer customer = customerRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Customer not found: %d", id)));
            return CustomerMapper.map(customer);
        } catch (Exception e) {
            log.error("Failed to find customer", e);
            throw e;
        }
    }

}
