package org.learn.strategy.service;

import org.learn.strategy.ShippingCostCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Shipping Service - Context class that uses Strategy pattern
 * Demonstrates Spring's dependency injection with Strategy pattern
 */
@Service
public class ShippingService {

    private ShippingCostCalculator currentStrategy;

    // Map of all available strategies (Spring will auto-inject all implementations)
    private final Map<String, ShippingCostCalculator> strategies;

    /**
     * Constructor injection with all available strategies
     * Spring automatically injects all beans implementing ShippingCostCalculator
     */
    @Autowired
    public ShippingService(Map<String, ShippingCostCalculator> strategies,
            @Qualifier("standardShipping") ShippingCostCalculator defaultStrategy) {
        this.strategies = strategies;
        this.currentStrategy = defaultStrategy;
    }

    /**
     * Calculate shipping cost using current strategy
     */
    public long calculateShippingCost(long distance) {
        return currentStrategy.calculateCost(distance);
    }

    /**
     * Change strategy at runtime
     */
    public void setStrategy(String strategyName) {
        ShippingCostCalculator strategy = strategies.get(strategyName);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown strategy: " + strategyName);
        }
        this.currentStrategy = strategy;
    }

    /**
     * Get current strategy name
     */
    public String getCurrentStrategyName() {
        return currentStrategy.getStrategyName();
    }

    /**
     * Get all available strategies
     */
    public Map<String, ShippingCostCalculator> getAllStrategies() {
        return strategies;
    }
}
