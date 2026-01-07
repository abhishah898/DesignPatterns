package org.learn.strategy.after.impl;

import org.learn.strategy.after.behavior.ShippingCostCalculator;

public class InternationalShipping implements ShippingCostCalculator {
    private final long CUSTOM_FEES = 2500;

    @Override
    public long calculateCost(long distance) {
        return distance*15 + this.CUSTOM_FEES;
    }
}