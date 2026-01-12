package org.learn.strategy.impl;

import org.learn.strategy.ShippingCostCalculator;
import org.springframework.stereotype.Component;

/**
 * International Shipping Strategy Implementation
 * Cost: distance * 15 + 2500 (customs fees)
 */
@Component("internationalShipping")
public class InternationalShipping implements ShippingCostCalculator {

    private static final long CUSTOMS_FEES = 2500;

    @Override
    public long calculateCost(long distance) {
        return distance * 15 + CUSTOMS_FEES;
    }

    @Override
    public String getStrategyName() {
        return "INTERNATIONAL";
    }
}
