package org.learn.strategy.after.impl;

import org.learn.strategy.after.behavior.ShippingCostCalculator;

public class ExpressShipping implements ShippingCostCalculator {
    public ExpressShipping() {
    }

    @Override
    public long calculateCost(long distance) {
        return distance*8 + 50;
    }
}