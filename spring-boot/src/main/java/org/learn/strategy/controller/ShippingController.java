package org.learn.strategy.controller;

import org.learn.strategy.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller to demonstrate Strategy Pattern
 * Provides endpoints to calculate shipping costs with different strategies
 */
@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    /**
     * Calculate shipping cost with current strategy
     * GET /api/shipping/calculate?distance=100
     */
    @GetMapping("/calculate")
    public ResponseEntity<Map<String, Object>> calculateCost(@RequestParam("distance") int distance) {
        long cost = shippingService.calculateShippingCost(distance);

        Map<String, Object> response = new HashMap<>();
        response.put("strategy", shippingService.getCurrentStrategyName());
        response.put("distance", distance);
        response.put("cost", cost);

        return ResponseEntity.ok(response);
    }

    /**
     * Calculate cost with a specific strategy
     * GET /api/shipping/calculate/standard?distance=100
     */
    @GetMapping("/calculate/{strategyType}")
    public ResponseEntity<Map<String, Object>> calculateWithStrategy(
            @PathVariable("strategyType") String strategyType,
            @RequestParam("distance") long distance) {

        // Map strategy type to bean name
        String strategyName = strategyType.toLowerCase() + "Shipping";
        shippingService.setStrategy(strategyName);

        long cost = shippingService.calculateShippingCost(distance);

        Map<String, Object> response = new HashMap<>();
        response.put("strategy", shippingService.getCurrentStrategyName());
        response.put("distance", distance);
        response.put("cost", cost);

        return ResponseEntity.ok(response);
    }

    /**
     * Change the default strategy
     * POST /api/shipping/strategy?type=expressShipping
     */
    @PostMapping("/strategy")
    public ResponseEntity<Map<String, String>> changeStrategy(@RequestParam("type") String type) {
        shippingService.setStrategy(type);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Strategy changed successfully");
        response.put("currentStrategy", shippingService.getCurrentStrategyName());

        return ResponseEntity.ok(response);
    }

    /**
     * Get all available strategies
     * GET /api/shipping/strategies
     */
    @GetMapping("/strategies")
    public ResponseEntity<Map<String, String>> getAvailableStrategies() {
        Map<String, String> strategies = new HashMap<>();

        shippingService.getAllStrategies().forEach((name, strategy) -> {
            strategies.put(name, strategy.getStrategyName());
        });

        return ResponseEntity.ok(strategies);
    }

    /**
     * Compare all strategies for a given distance
     * GET /api/shipping/compare?distance=100
     */
    @GetMapping("/compare")
    public ResponseEntity<Map<String, Object>> compareStrategies(@RequestParam("distance") long distance) {
        Map<String, Object> comparison = new HashMap<>();
        comparison.put("distance", distance);

        Map<String, Long> costs = new HashMap<>();
        shippingService.getAllStrategies().forEach((name, strategy) -> {
            costs.put(strategy.getStrategyName(), strategy.calculateCost(distance));
        });

        comparison.put("costs", costs);

        return ResponseEntity.ok(comparison);
    }
}