package com.barbers.schedule.mapper;

import com.barbers.schedule.domain.model.Services;
import com.barbers.schedule.dto.ServicesDTO;

public class ServiceMapper {

    public static Services map(final ServicesDTO servicesDTO) {
        return Services.builder()
                .id(servicesDTO.getId())
                .name(servicesDTO.getName())
                .description(servicesDTO.getDescription())
                .cost(servicesDTO.getCost())
                .build();
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
