package com.project.parkinglot.strategy.fee;

import org.springframework.stereotype.Component;

@Component
public class BikeFeeStrategy implements FeeStrategy {

    @Override
    public Double calculateFee(long hours) {
        return hours*20.0;
    }
}
