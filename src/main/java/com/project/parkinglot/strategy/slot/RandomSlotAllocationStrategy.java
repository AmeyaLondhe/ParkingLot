package com.project.parkinglot.strategy.slot;

import com.project.parkinglot.entity.ParkingSlot;
import com.project.parkinglot.entity.SlotStatus;
import com.project.parkinglot.entity.VehicleType;
import com.project.parkinglot.exception.NoSlotsAvailableException;
import com.project.parkinglot.repository.ParkingSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class RandomSlotAllocationStrategy implements SlotAllocationStrategy {

    private final ParkingSlotRepository parkingSlotRepository;

    @Override
    public ParkingSlot allocateSlot(VehicleType vehicleType) {
        List<ParkingSlot> availableLots = parkingSlotRepository.findAvailableSlotsforUpdate(vehicleType, SlotStatus.FREE)
                .orElseThrow(()-> new NoSlotsAvailableException("No slots available"));

        return availableLots.get(new Random().nextInt(availableLots.size()));
    }
}
