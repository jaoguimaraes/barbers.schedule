package com.barbers.schedule.repository;

import com.barbers.schedule.domain.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulesRepository extends JpaRepository<Schedules, Long> {
}
