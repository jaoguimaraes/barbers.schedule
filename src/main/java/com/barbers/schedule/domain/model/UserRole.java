package com.barbers.schedule.domain.model;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole(final String role) {
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
