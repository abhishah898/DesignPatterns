package org.learn.strategy;

/**
 * Strategy Interface for calculating shipping costs
 */
public interface ShippingCostCalculator {
    long calculateCost(long distance);

    /**
     * Returns the type/name of the shipping strategy
     */
    String getStrategyName();
}
