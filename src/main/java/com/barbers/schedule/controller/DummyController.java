package com.barbers.schedule.controller;

import com.barbers.schedule.domain.model.*;
import com.barbers.schedule.repository.*;
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
public class DummyController {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final EmployeeRepository employeeRepository;
    private final SchedulesRepository schedulesRepository;
    private final ServicesRepository servicesRepository;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllAddress() {
        return new ResponseEntity<>(addressRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedules>> getAllSchedules() {
        return new ResponseEntity<>(schedulesRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/services")
    public ResponseEntity<List<Services>> getAllServices() {
        return new ResponseEntity<>(servicesRepository.findAll(), HttpStatus.OK);
    }

}
