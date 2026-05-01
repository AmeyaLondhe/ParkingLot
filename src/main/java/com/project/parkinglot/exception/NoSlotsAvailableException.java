package com.project.parkinglot.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class NoSlotsAvailableException extends RuntimeException{

    public NoSlotsAvailableException(String message) {
        super(message);
    }
}
