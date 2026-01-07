package org.learn.strategy.before;

public class Main {
    public static void main(String[] args) {
        ShippingCostCalculator shippingCostCalculator = new ShippingCostCalculator();

        System.out.println("Shipping cost for Standard.");
        System.out.println(shippingCostCalculator.calculateCost("standard", 100));

        System.out.println("Shipping cost for Express.");
        System.out.println(shippingCostCalculator.calculateCost("express", 100));

        System.out.println("Shipping cost for International.");
        System.out.println(shippingCostCalculator.calculateCost("international", 100));

    }

}