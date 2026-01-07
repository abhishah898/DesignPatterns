package org.learn.strategy.after.service;

import org.learn.strategy.after.behavior.ShippingCostCalculator;

public class ShippingService {
    private ShippingCostCalculator strategy;

    public ShippingService(ShippingCostCalculator strategy) {
        this.strategy = strategy;
    }

    public long calculateShippingCost(long distance) {
        return strategy.calculateCost(distance);
    }

    // optional: runtime switch
    public void changeStrategy(ShippingCostCalculator strategy) {
        this.strategy = strategy;
    }
}