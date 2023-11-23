package com.barbers.schedule.repository;

import com.barbers.schedule.domain.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Services, Long> {
}
