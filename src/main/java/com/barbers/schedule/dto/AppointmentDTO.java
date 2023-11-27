package com.barbers.schedule.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@Builder
@ToString
public class AppointmentDTO {

    private Long id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String clientName;
    private EmployeeDTO employee;

}
