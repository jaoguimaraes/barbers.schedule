package com.barbers.schedule.controller;

import com.barbers.schedule.dto.CustomerDTO;
import com.barbers.schedule.dto.EmployeeDTO;
import com.barbers.schedule.service.EmployeeService;
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
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    private ResponseEntity<EmployeeDTO> createEmployee(@RequestBody final EmployeeDTO employee) {
        log.info("Creating a new employee: {}", employee);
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    private ResponseEntity<EmployeeDTO> findEmployeeById(@PathVariable final Long id) {
        log.info("Searching employee by id: {}", id);
        return new ResponseEntity<>(employeeService.findEmployeeById(id), HttpStatus.OK);
    }



}
