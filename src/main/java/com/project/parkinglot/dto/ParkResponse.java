package com.project.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkResponse {
    private String ticketNumber;
    private String parkingSlot;
    private Integer floorNumber;
}
