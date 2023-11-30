package com.barbers.schedule.dto;

import com.barbers.schedule.domain.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
