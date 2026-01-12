package org.learn;

import org.learn.strategy.service.ShippingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot Application demonstrating Strategy Design Pattern
 * 
 * The Strategy Pattern allows selecting an algorithm at runtime.
 * In this example, we have different shipping cost calculation strategies.
 * 
 * Spring Boot enhancements:
 * - Strategies are Spring Components (auto-discovered via component scanning)
 * - Service uses dependency injection to get all available strategies
 * - Runtime strategy switching via REST API
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * CommandLineRunner to demonstrate Strategy pattern on application startup
     */
    @Bean
    public CommandLineRunner demo(ShippingService shippingService) {
        return args -> {
            System.out.println("\n========================================");
            System.out.println("Strategy Pattern Demo - Spring Boot");
            System.out.println("========================================\n");

            long distance = 100;

            // Test Standard Shipping
            shippingService.setStrategy("standardShipping");
            System.out.println("Standard Shipping (distance=" + distance + "): $"
                    + shippingService.calculateShippingCost(distance));

            // Test Express Shipping
            shippingService.setStrategy("expressShipping");
            System.out.println("Express Shipping (distance=" + distance + "): $"
                    + shippingService.calculateShippingCost(distance));

            // Test International Shipping
            shippingService.setStrategy("internationalShipping");
            System.out.println("International Shipping (distance=" + distance + "): $"
                    + shippingService.calculateShippingCost(distance));

            System.out.println("\n========================================");
            System.out.println("Available REST API Endpoints:");
            System.out.println("========================================");
            System.out.println("GET  /api/shipping/calculate?distance=100");
            System.out.println("GET  /api/shipping/calculate/{strategy}?distance=100");
            System.out.println("     (strategy: standard, express, international)");
            System.out.println("GET  /api/shipping/compare?distance=100");
            System.out.println("GET  /api/shipping/strategies");
            System.out.println("POST /api/shipping/strategy?type=expressShipping");
            System.out.println("========================================\n");
        };
    }
}