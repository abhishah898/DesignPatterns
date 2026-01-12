package org.learn.strategy.after;

import org.learn.strategy.after.behavior.ShippingCostCalculator;
import org.learn.strategy.after.impl.ExpressShipping;
import org.learn.strategy.after.impl.InternationalShipping;
import org.learn.strategy.after.impl.StandardShipping;
import org.learn.strategy.after.service.ShippingService;

public class Main {
    public static void main(String[] args) {
        ShippingService service =
                new ShippingService(new StandardShipping());

        System.out.println("Standard Shipping:");
        System.out.println(service.calculateShippingCost(100));

        service.changeStrategy(new ExpressShipping());
        System.out.println("Express Shipping:");
        System.out.println(service.calculateShippingCost(100));

        service.changeStrategy(new InternationalShipping());
        System.out.println("International Shipping:");
        System.out.println(service.calculateShippingCost(100));
    }
}