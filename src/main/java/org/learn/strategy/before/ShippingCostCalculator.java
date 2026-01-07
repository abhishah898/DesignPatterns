package org.learn.strategy.before;

public class ShippingCostCalculator {

    private final long CUSTOM_FEES = 2500;

    public ShippingCostCalculator() {
    }

    public long calculateCost(String type, long distance) {
        long cost=0;
        if (type.equals("standard"))
            cost = standardShipping(distance);
        else if (type.equals("express"))
            cost = expressShipping(distance);
        else if (type.equals("international"))
            cost = internationalShipping(distance);
        return cost;
    }

    public long standardShipping(long distance) {
        return distance*5;
    }

    public long expressShipping(long distance) {
        return distance*8 + 50;
    }

    public long internationalShipping(long distance) {
        return distance*15 + this.CUSTOM_FEES;
    }
}