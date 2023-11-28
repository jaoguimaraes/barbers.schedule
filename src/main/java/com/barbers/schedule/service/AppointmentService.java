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

        boolean isEmployeeAbleToScheduleAppointment = canEmployeeScheduleAppointment(employee, appointmentDTO);

        if (!isEmployeeAbleToScheduleAppointment) {
            throw new IllegalArgumentException(
                    String.format("Employee has no time in this period: %s - %s",
                            appointmentDTO.getStartDate(), appointmentDTO.getEndDate()));
        }
    }

    private static boolean canEmployeeScheduleAppointment(final Employee employee, final AppointmentDTO appointmentDTO) {
        List<String> startDateConverted = extractHoursAndMinutes(employee.getWorkStartDate());
        List<String> lunchStartDateConverted = extractHoursAndMinutes(employee.getLunchStartDate());
        List<String> lunchEndDateConverted = extractHoursAndMinutes(employee.getLunchEndDate());
        List<String> endDateConverted = extractHoursAndMinutes(employee.getWorkEndDate());

        hasUserAnotherAppointmentInThisPeriod(employee, appointmentDTO);

        boolean isAfterStartDate = compareHoursAndMinutes(
                Integer.parseInt(startDateConverted.get(0)),
                Integer.parseInt(startDateConverted.get(1)),
                appointmentDTO.getStartDate().getHour(),
                appointmentDTO.getStartDate().getMinute()
        );

        boolean isBeforeLunch = compareHoursAndMinutes(
                appointmentDTO.getEndDate().getHour(),
                appointmentDTO.getEndDate().getMinute(),
                Integer.parseInt(lunchStartDateConverted.get(0)),
                Integer.parseInt(lunchStartDateConverted.get(1))
        );

        boolean isAfterLunch = compareHoursAndMinutes(
                appointmentDTO.getEndDate().getHour(),
                appointmentDTO.getEndDate().getMinute(),
                Integer.parseInt(endDateConverted.get(0)),
                Integer.parseInt(endDateConverted.get(1))
        );

        boolean isBeforeEndWork = compareHoursAndMinutes(
                Integer.parseInt(lunchEndDateConverted.get(0)),
                Integer.parseInt(lunchEndDateConverted.get(1)),
                appointmentDTO.getStartDate().getHour(),
                appointmentDTO.getStartDate().getMinute()
        );

        return (isAfterStartDate && isBeforeLunch || isAfterLunch && isBeforeEndWork);

    }

    private static void hasUserAnotherAppointmentInThisPeriod(final Employee employee, final AppointmentDTO appointmentDTO) {
        if (!employee.getAppointments().isEmpty()) {
            boolean hasAppointments = employee.getAppointments()
                    .stream()
                    .anyMatch(employeeAppointment -> isAppointmentAtSameTime(appointmentDTO, employeeAppointment)
                            || (isAppointmentAtSameTimeOrInside(appointmentDTO, employeeAppointment)
                            || isAppointmentBeforeBeginAndBeforeEnd(appointmentDTO, employeeAppointment))
                            || isAppointmentAfterBeginAndAfterEnd(appointmentDTO, employeeAppointment)
                            || isAppointmentBeforeBeginAndAfterEnd(appointmentDTO, employeeAppointment)
                    );
            if (hasAppointments) {
                throw new IllegalArgumentException(
                        String.format("Employee %d can not attend the appointment during this period: %s - %s",
                                employee.getId(), appointmentDTO.getStartDate(), appointmentDTO.getEndDate()));
            }
        }
    }

    private static boolean isAppointmentAtSameTime(final AppointmentDTO appointmentDTO,
                                                   final Appointment employeeAppointment) {
        return appointmentDTO.getStartDate().isEqual(employeeAppointment.getStartDate())
                && appointmentDTO.getEndDate().isEqual(employeeAppointment.getEndDate());
    }

    private static boolean isAppointmentAtSameTimeOrInside(final AppointmentDTO appointmentDTO,
                                                           final Appointment employeeAppointment) {
        return isAfterOrEqualDate(appointmentDTO.getStartDate(), employeeAppointment.getStartDate())
                && isBeforeOrEqualDate(appointmentDTO.getEndDate(), employeeAppointment.getEndDate());
    }

    private static boolean isAppointmentBeforeBeginAndBeforeEnd(final AppointmentDTO appointmentDTO,
                                                                final Appointment employeeAppointment) {
        return (isBeforeOrEqualDate(appointmentDTO.getStartDate(), employeeAppointment.getStartDate()))
                && isAfterOrEqualDate(appointmentDTO.getEndDate(), employeeAppointment.getStartDate())
                && isBeforeOrEqualDate(appointmentDTO.getEndDate(), employeeAppointment.getEndDate());
    }

    private static boolean isAppointmentAfterBeginAndAfterEnd(final AppointmentDTO appointmentDTO,
                                                              final Appointment employeeAppointment) {
        return isAfterOrEqualDate(appointmentDTO.getStartDate(), employeeAppointment.getStartDate())
                && isBeforeOrEqualDate(appointmentDTO.getStartDate(), employeeAppointment.getEndDate())
                && isAfterOrEqualDate(appointmentDTO.getEndDate(), employeeAppointment.getEndDate());
    }

    private static boolean isAppointmentBeforeBeginAndAfterEnd(final AppointmentDTO appointmentDTO,
                                                               final Appointment appointment) {
        return isBeforeOrEqualDate(appointmentDTO.getStartDate(), appointment.getStartDate())
                && isAfterOrEqualDate(appointmentDTO.getEndDate(), appointment.getEndDate());
    }

    private static boolean isBeforeOrEqualDate(final LocalDateTime startDate, final LocalDateTime endDate) {
        return startDate.isBefore(endDate) || startDate.isEqual(endDate);

    }

    private static boolean isAfterOrEqualDate(final LocalDateTime startDate, final LocalDateTime endDate) {
        return startDate.isAfter(endDate) || startDate.isEqual(endDate);

    }

    private static List<String> extractHoursAndMinutes(final String date) {
        return Arrays.stream(date.split(":"))
                .collect(Collectors.toList());
    }

    private static boolean compareHoursAndMinutes(
            final Integer hour,
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
