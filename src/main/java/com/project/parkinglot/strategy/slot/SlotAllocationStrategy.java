package com.project.parkinglot.strategy.slot;

import com.project.parkinglot.entity.ParkingSlot;
import com.project.parkinglot.entity.VehicleType;

public interface SlotAllocationStrategy {
    public ParkingSlot allocateSlot(VehicleType vehicleType);
}
