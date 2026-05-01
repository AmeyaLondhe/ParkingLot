package com.project.parkinglot.dto;

import com.project.parkinglot.entity.SlotStrategyType;
import com.project.parkinglot.entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkRequest {
    private String vehicleNo;
    private VehicleType vehicleType;
    private SlotStrategyType strategy;
}
