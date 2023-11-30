package com.barbers.schedule.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@ToString
public class EmployeeDTO implements Serializable {

    private Long id;
    private String name;
    private String workStartDate;
    private String workEndDate;
    private String lunchStartDate;
    private String lunchEndDate;
    private Boolean active;
    private List<ServicesDTO> services;

}
