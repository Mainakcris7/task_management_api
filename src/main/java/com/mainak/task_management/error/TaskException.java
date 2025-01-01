package com.mainak.task_management.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskException extends RuntimeException {
    private HttpStatus status;

    public TaskException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
