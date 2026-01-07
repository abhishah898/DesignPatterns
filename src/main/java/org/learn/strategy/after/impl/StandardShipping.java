package org.learn.strategy.after.impl;

import org.learn.strategy.after.behavior.ShippingCostCalculator;

public class StandardShipping implements ShippingCostCalculator {
    @Override
    public long calculateCost(long distance) {
        return distance*5;
    }
}