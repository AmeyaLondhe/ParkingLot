package com.project.parkinglot.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class InvalidTicketException extends RuntimeException{

    public InvalidTicketException(String message) {
        super(message);
    }
}
