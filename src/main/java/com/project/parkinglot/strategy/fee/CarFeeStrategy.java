package com.project.parkinglot.strategy.fee;

import org.springframework.stereotype.Component;

@Component
public class CarFeeStrategy implements FeeStrategy {
    @Override
    public Double calculateFee(long hours) {
        return hours*30.0;
    }
}
