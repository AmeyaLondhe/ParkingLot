package com.project.parkinglot.strategy.slot;

import com.project.parkinglot.entity.ParkingSlot;
import com.project.parkinglot.entity.SlotStatus;
import com.project.parkinglot.entity.VehicleType;
import com.project.parkinglot.exception.NoSlotsAvailableException;
import com.project.parkinglot.repository.ParkingSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NearestSlotAllocationStrategy implements SlotAllocationStrategy {

    private final ParkingSlotRepository parkingSlotRepository;

    @Override
    public ParkingSlot allocateSlot(VehicleType vehicleType) {
       List<ParkingSlot> availableSlots = parkingSlotRepository.findAvailableSlotsforUpdate(vehicleType, SlotStatus.FREE)
               .orElseThrow(()-> new NoSlotsAvailableException("No nearest slot available"));
       return availableSlots.get(0);
    }
}
