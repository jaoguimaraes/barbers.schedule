package com.barbers.schedule.exception;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class ApiError {

    private String title;
    private String message;
}
