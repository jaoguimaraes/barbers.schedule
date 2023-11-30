package com.barbers.schedule.mapper;

import com.barbers.schedule.domain.model.Address;
import com.barbers.schedule.dto.AddressDTO;

public class AddressMapper {
    public static Address map(final AddressDTO addressDTO) {
        return Address.builder()
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .country(addressDTO.getCountry())
                .state(addressDTO.getState())
                .zipCode(addressDTO.getZipCode())
                .number(addressDTO.getNumber())
                .build();
    }

    public static AddressDTO map(final Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }
}
