package com.project.parkinglot.strategy.fee;

public interface FeeStrategy {
    Double calculateFee(long hours);
}
