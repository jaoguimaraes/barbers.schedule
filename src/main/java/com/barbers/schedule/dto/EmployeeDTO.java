package com.barbers.schedule.dto;

import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Builder
@ToString
public class EmployeeDTO implements Serializable {

    private Long id;
    private String name;
    private Date workStartDate;
    private Date workEndDate;
    private Date lunchStartDate;
    private Date lunchEndDate;
    private Boolean active;

}
