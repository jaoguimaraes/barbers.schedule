package com.barbers.schedule.service;

import com.barbers.schedule.domain.model.Appointment;
import com.barbers.schedule.domain.model.Employee;
import com.barbers.schedule.dto.AppointmentDTO;
import com.barbers.schedule.dto.EmployeeDTO;
import com.barbers.schedule.mapper.AppointmentMapper;
import com.barbers.schedule.repository.AppointmentRepository;
import com.barbers.schedule.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;

    public AppointmentDTO createAppointment(final AppointmentDTO appointmentDTO) {
        try {
            EmployeeDTO employee = appointmentDTO.getEmployee();
            if (Objects.isNull(employee)) {
                throw new IllegalArgumentException(String.format("There is no employee for this appointment"));
            }
            Employee employeeFound = employeeRepository.findById(employee.getId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            String.format("Could not found employeeId: {}", appointmentDTO.getEmployee())));

            Appointment appointment = AppointmentMapper.map(appointmentDTO);
            appointment.setEmployee(employeeFound);
            return AppointmentMapper.map(appointmentRepository.save(appointment));
        } catch (Exception e) {
            log.error("Failed to create appointment", e);
            throw e;
        }
    }

    public List<AppointmentDTO> findAllAppointments() {
        try {
            return appointmentRepository.findAll()
                    .stream()
                    .map(AppointmentMapper::map)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Failed to list appointments");
            throw e;
        }
    }
}
