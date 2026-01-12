package org.learn.strategy.impl;

import org.learn.strategy.ShippingCostCalculator;
import org.springframework.stereotype.Component;

/**
 * Express Shipping Strategy Implementation
 * Cost: distance * 8 + 50 (express fee)
 */
@Component("expressShipping")
public class ExpressShipping implements ShippingCostCalculator {

    private static final long EXPRESS_FEE = 50;

    @Override
    public long calculateCost(long distance) {
        return distance * 8 + EXPRESS_FEE;
    }

    @Override
    public String getStrategyName() {
        return "EXPRESS";
    }
}
