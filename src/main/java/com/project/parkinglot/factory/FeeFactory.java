package com.project.parkinglot.factory;

import com.project.parkinglot.entity.VehicleType;
import com.project.parkinglot.strategy.fee.BikeFeeStrategy;
import com.project.parkinglot.strategy.fee.CarFeeStrategy;
import com.project.parkinglot.strategy.fee.FeeStrategy;
import com.project.parkinglot.strategy.fee.TruckFeeStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FeeFactory {
    private final BikeFeeStrategy bike;
    private final TruckFeeStrategy truck;
    private final CarFeeStrategy car;

    public FeeStrategy getFeeStrategy(VehicleType vehicleType) {
        return switch(vehicleType){
            case BIKE -> bike;
            case TRUCK -> truck;
            case CAR -> car;
        };
    }
}
