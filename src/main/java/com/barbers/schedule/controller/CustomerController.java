package com.barbers.schedule.controller;

import com.barbers.schedule.dto.CustomerDTO;
import com.barbers.schedule.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    private ResponseEntity<CustomerDTO> createCustomer(@RequestBody final CustomerDTO customer) {
        log.info("Creating a new customer: {}", customer);
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<CustomerDTO> findCustomerById(@PathVariable final Long id) {
        log.info("Searching customer by id: {}", id);
        return new ResponseEntity<>(customerService.findCustomerById(id), HttpStatus.OK);
    }

}
