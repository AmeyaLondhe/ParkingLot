package com.project.parkinglot.factory;

import com.project.parkinglot.entity.ParkingSlot;
import com.project.parkinglot.entity.SlotStatus;
import com.project.parkinglot.entity.SlotStrategyType;
import com.project.parkinglot.entity.VehicleType;
import com.project.parkinglot.strategy.slot.NearestSlotAllocationStrategy;
import com.project.parkinglot.strategy.slot.RandomSlotAllocationStrategy;
import com.project.parkinglot.strategy.slot.SlotAllocationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SlotAllocationFactory {

    private final NearestSlotAllocationStrategy nearest;
    private final RandomSlotAllocationStrategy random;

    public SlotAllocationStrategy getParkingSlot(SlotStrategyType strategy) {
        if(strategy == null)
            return random;
        return switch(strategy){
            case NEAREST -> nearest;
            case RANDOM -> random;
            default -> random;
        };
    }
}
