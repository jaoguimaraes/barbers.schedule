package com.barbers.schedule.controller;

import com.barbers.schedule.dto.AppointmentDTO;
import com.barbers.schedule.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/create")
    private ResponseEntity<AppointmentDTO> createAppointment(@RequestBody final AppointmentDTO appointment) {
        log.info("Creating a new appointment: {}", appointment);
        return new ResponseEntity<>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<AppointmentDTO>> findAllAppointments() {
        log.info("Finding all appointments");
        return new ResponseEntity<>(appointmentService.findAllAppointments(), HttpStatus.OK);
    }
}
