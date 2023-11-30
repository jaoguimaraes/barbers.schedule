package com.barbers.schedule.mapper;

import com.barbers.schedule.domain.model.Appointment;
import com.barbers.schedule.dto.AppointmentDTO;

public class AppointmentMapper {

    public static Appointment map(final AppointmentDTO appointmentDTO) {
        return Appointment.builder()
                .id(appointmentDTO.getId())
                .name(appointmentDTO.getName())
                .description(appointmentDTO.getDescription())
                .startDate(appointmentDTO.getStartDate())
                .endDate(appointmentDTO.getEndDate())
                .clientName(appointmentDTO.getClientName())
                .employee(EmployeeMapper.map(appointmentDTO.getEmployee()))
                .build();
    }

    public static AppointmentDTO map(final Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .name(appointment.getName())
                .description(appointment.getDescription())
                .startDate(appointment.getStartDate())
                .endDate(appointment.getEndDate())
                .clientName(appointment.getClientName())
                .employee(EmployeeMapper.map(appointment.getEmployee()))
                .build();
    }

}
