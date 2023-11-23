package com.barbers.schedule.controller;

import com.barbers.schedule.domain.model.Customer;
import com.barbers.schedule.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

}
