package com.barbers.schedule.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiError {

    private String title;
    private String message;
}
