package com.barbers.schedule.mapper;

import com.barbers.schedule.domain.model.Employee;
import com.barbers.schedule.dto.EmployeeDTO;

import java.util.Objects;
import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeDTO map(final Employee employee) {
        EmployeeDTO.EmployeeDTOBuilder employeeDTOBuilder = EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .workStartDate(employee.getWorkStartDate())
                .workEndDate(employee.getWorkEndDate())
                .lunchStartDate(employee.getLunchStartDate())
                .lunchEndDate(employee.getLunchEndDate())
                .active(employee.getActive());

        if (Objects.nonNull(employee.getServices())) {
            employeeDTOBuilder.services(
                    employee.getServices()
                            .stream()
                            .map(ServiceMapper::map)
                            .collect(Collectors.toList())
            );
        }

        return employeeDTOBuilder.build();
    }

    public static Employee map(final EmployeeDTO employeeDTO) {
        Employee.EmployeeBuilder employeeBuilder = Employee.builder()
                .id(employeeDTO.getId())
                .name(employeeDTO.getName())
                .workStartDate(employeeDTO.getWorkStartDate())
                .workEndDate(employeeDTO.getWorkEndDate())
                .lunchStartDate(employeeDTO.getLunchStartDate())
                .lunchEndDate(employeeDTO.getLunchEndDate())
                .active(employeeDTO.getActive());

        if (Objects.nonNull(employeeDTO.getServices()) && !employeeDTO.getServices().isEmpty()) {
            employeeBuilder.services(
                    employeeDTO.getServices()
                            .stream()
                            .map(ServiceMapper::map)
                            .collect(Collectors.toSet())
            );
        }
        return employeeBuilder.build();
    }

}
