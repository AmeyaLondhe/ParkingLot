package com.project.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnparkResponse {
    private String ticketNumber;
    private Long parkDuration;
    private Double amount;
}
