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

import java.time.LocalDateTime;
import java.util.Arrays;
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
            Employee employeeFound = getEmployee(appointmentDTO, employee);
            validateEmployeeIsAvailable(appointmentDTO, employeeFound);

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

    private Employee getEmployee(AppointmentDTO appointmentDTO, EmployeeDTO employee) {
        Employee employeeFound = employeeRepository.findById(employee.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Could not found employeeId: {}", appointmentDTO.getEmployee())));
        return employeeFound;
    }

    private void validateEmployeeIsAvailable(AppointmentDTO appointmentDTO, Employee employee) {
        if (!employee.isActive()) {
            throw new IllegalArgumentException(String.format("Employee is not active: %d", employee.getId()));
        }

        boolean isEmployeeAbleToScheduleAppointment = canEmployeeScheduleAppointment(
                employee.getWorkStartDate(),
                employee.getLunchStartDate(),
                employee.getLunchEndDate(),
                employee.getWorkEndDate(),
                appointmentDTO.getStartDate(),
                appointmentDTO.getEndDate()
        );

        if (!isEmployeeAbleToScheduleAppointment) {
            throw new IllegalArgumentException(
                    String.format("Employee has no time in this period: %s - %s",
                            appointmentDTO.getStartDate(), appointmentDTO.getEndDate()));
        }
    }

    private boolean canEmployeeScheduleAppointment(
            final String startDate,
            final String lunchStartDate,
            final String lunchEndDate,
            final String endDate,
            final LocalDateTime startDateAppointment,
            final LocalDateTime endDateAppointment) {

        List<String> startDateConverted = convertDate(startDate);
        List<String> lunchStartDateConverted = convertDate(lunchStartDate);
        List<String> lunchEndDateConverted = convertDate(lunchEndDate);
        List<String> endDateConverted = convertDate(endDate);

        boolean isAfterStartDate = compareDateWithMinutes(
                Integer.parseInt(startDateConverted.get(0)),
                Integer.parseInt(startDateConverted.get(1)),
                startDateAppointment.getHour(),
                startDateAppointment.getMinute()
        );

        boolean isBeforeLunch = compareDateWithMinutes(
                endDateAppointment.getHour(),
                endDateAppointment.getMinute(),
                Integer.parseInt(lunchStartDateConverted.get(0)),
                Integer.parseInt(lunchStartDateConverted.get(1))
        );

        boolean isAfterLunch = compareDateWithMinutes(
                endDateAppointment.getHour(),
                endDateAppointment.getMinute(),
                Integer.parseInt(endDateConverted.get(0)),
                Integer.parseInt(endDateConverted.get(1))
        );

        boolean isBeforeEndWork = compareDateWithMinutes(
                Integer.parseInt(lunchEndDateConverted.get(0)),
                Integer.parseInt(lunchEndDateConverted.get(1)),
                startDateAppointment.getHour(),
                startDateAppointment.getMinute()
        );

        return (isAfterStartDate && isBeforeLunch || isAfterLunch && isBeforeEndWork);

    }

    private List<String> convertDate(final String date) {
        return Arrays.stream(date.split(":"))
                .collect(Collectors.toList());
    }

    private boolean compareDateWithMinutes(final Integer hour,
                                           final Integer minute,
                                           final Integer appointmentHour,
                                           final Integer appointmentMinutes) {
        if (hour == appointmentHour) {
            if (minute < appointmentMinutes) {
                return true;
            }
        }
        if (hour < appointmentHour) {
            return true;
        }
        return false;
    }

}
