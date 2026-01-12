package org.learn.strategy.impl;

import org.learn.strategy.ShippingCostCalculator;
import org.springframework.stereotype.Component;

/**
 * Standard Shipping Strategy Implementation
 * Cost: distance * 5
 */
@Component("standardShipping")
public class StandardShipping implements ShippingCostCalculator {

    @Override
    public long calculateCost(long distance) {
        return distance * 5;
    }

    @Override
    public String getStrategyName() {
        return "STANDARD";
    }
}
