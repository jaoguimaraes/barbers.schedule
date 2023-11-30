package com.barbers.schedule.service;

import com.barbers.schedule.domain.model.Employee;
import com.barbers.schedule.dto.EmployeeDTO;
import com.barbers.schedule.mapper.EmployeeMapper;
import com.barbers.schedule.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeDTO createEmployee(final EmployeeDTO employeeDTO) {
        try {
            Employee employee = EmployeeMapper.map(employeeDTO);
            return EmployeeMapper.map(employeeRepository.save(employee));
        } catch(Exception e) {
            log.error("Failed to create employee", e);
            throw e;
        }
    }

    public EmployeeDTO findEmployeeById(final Long id) {
        try {
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException(String.format("Employee not found: %d", id)));
            return EmployeeMapper.map(employee);
        } catch (Exception e) {
            log.error("Failed to find employee", e);
            throw e;
        }
    }

}
