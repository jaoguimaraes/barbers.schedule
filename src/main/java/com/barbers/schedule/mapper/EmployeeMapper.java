package com.barbers.schedule.mapper;

import com.barbers.schedule.domain.model.Employee;
import com.barbers.schedule.domain.model.Services;
import com.barbers.schedule.dto.EmployeeDTO;
import com.barbers.schedule.dto.ServicesDTO;

import java.util.List;
import java.util.Objects;
import java.util.Set;
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
            employeeDTOBuilder.services(map(employee.getServices()));
        }

        return employeeDTOBuilder.build();
    }

    public static Employee map(final EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setWorkStartDate(employeeDTO.getWorkStartDate());
        employee.setWorkEndDate(employeeDTO.getWorkEndDate());
        employee.setLunchStartDate(employeeDTO.getLunchStartDate());
        employee.setLunchEndDate(employeeDTO.getLunchEndDate());
        employee.setActive(employeeDTO.getActive());
        if (!employeeDTO.getServices().isEmpty()) {
            employee.setServices(map(employeeDTO.getServices()));
        }
        return employee;
    }

    public static Set<Services> map(final List<ServicesDTO> servicesDTOs) {
        return servicesDTOs.stream()
                .map(EmployeeMapper::map)
                .collect(Collectors.toSet());
    }

    public static Services map(final ServicesDTO servicesDTO) {
        return Services.builder()
                .id(servicesDTO.getId())
                .name(servicesDTO.getName())
                .description(servicesDTO.getDescription())
                .cost(servicesDTO.getCost())
                .build();
    }

    public static List<ServicesDTO> map(final Set<Services> services) {
        return services.stream()
                .map(EmployeeMapper::map)
                .collect(Collectors.toList());
    }

    public static ServicesDTO map(final Services service) {
        return ServicesDTO.builder()
                .id(service.getId())
                .name(service.getName())
                .description(service.getDescription())
                .cost(service.getCost())
                .build();
    }

}
