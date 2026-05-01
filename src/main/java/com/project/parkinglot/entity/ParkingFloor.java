package com.project.parkinglot.entity;

import lombok.Data;

import java.util.List;

@Data
public class ParkingFloor {
    Integer floorNo;
    List<ParkingSlot> parkingSlots;
}
