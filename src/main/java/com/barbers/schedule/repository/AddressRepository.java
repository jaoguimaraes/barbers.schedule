package com.barbers.schedule.repository;

import com.barbers.schedule.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
