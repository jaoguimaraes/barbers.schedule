package com.barbers.schedule.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@Builder
@ToString
public class ServicesDTO {

    private Long id;
    private String name;
    private String description;
    private BigDecimal cost;
}
