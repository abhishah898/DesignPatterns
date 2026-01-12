# Strategy Pattern - Spring Boot Implementation

## Overview
This project demonstrates the **Strategy Design Pattern** implemented using Spring Boot framework. The Strategy pattern allows selecting an algorithm at runtime without changing the client code.

## Pattern Structure

### 1. Strategy Interface
- `ShippingCostCalculator` - Defines the contract for all shipping strategies

### 2. Concrete Strategies
- `StandardShipping` - Basic shipping: distance × 5
- `ExpressShipping` - Fast shipping: distance × 8 + 50
- `InternationalShipping` - Global shipping: distance × 15 + 2500

### 3. Context
- `ShippingService` - Uses strategies to calculate shipping costs

### 4. Client
- `ShippingController` - REST API to interact with the service
- `Main` - Application entry point with demo

## Spring Boot Enhancements

### Dependency Injection
Instead of manually creating strategy objects, Spring automatically:
- Discovers all `@Component` implementations of `ShippingCostCalculator`
- Injects them into `ShippingService` via constructor injection
- Manages the lifecycle of all beans

### Component Scanning
All strategies are annotated with `@Component`:
```java
@Component("standardShipping")
public class StandardShipping implements ShippingCostCalculator { ... }
```

### Service Layer
The service is annotated with `@Service`:
```java
@Service
public class ShippingService {
    private final Map<String, ShippingCostCalculator> strategies;
    // Spring auto-injects all implementations
}
```

## REST API Endpoints

### Calculate with Current Strategy
```
GET /api/shipping/calculate?distance=100
```

### Calculate with Specific Strategy
```
GET /api/shipping/calculate/standard?distance=100
GET /api/shipping/calculate/express?distance=100
GET /api/shipping/calculate/international?distance=100
```

### Compare All Strategies
```
GET /api/shipping/compare?distance=100
```

### Get Available Strategies
```
GET /api/shipping/strategies
```

### Change Default Strategy
```
POST /api/shipping/strategy?type=expressShipping
```

## Running the Application

### Build and Run
```bash
cd spring-boot
mvn clean install
mvn spring-boot:run
```

### Test Endpoints
```bash
# Calculate with standard shipping
curl "http://localhost:8080/api/shipping/calculate?distance=100"

# Compare all strategies
curl "http://localhost:8080/api/shipping/compare?distance=100"

# Calculate with express shipping
curl "http://localhost:8080/api/shipping/calculate/express?distance=100"
```

## Key Benefits of Spring Boot Implementation

1. **Automatic Discovery**: Spring finds all strategy implementations automatically
2. **Loose Coupling**: Strategies are injected, not hardcoded
3. **Easy Extension**: Add new strategies by creating `@Component` classes
4. **Runtime Switching**: Change strategies without restarting the application
5. **RESTful API**: Expose pattern functionality via HTTP endpoints

## Comparison with Plain Java

| Aspect | Plain Java | Spring Boot |
|--------|-----------|-------------|
| Object Creation | Manual (`new StandardShipping()`) | Automatic (DI) |
| Strategy Discovery | Manual registration | Auto-scanning |
| Lifecycle | Manual management | Spring-managed |
| Configuration | Hardcoded | Externalized |
| API Exposure | Manual setup | `@RestController` |

## Example Output

When you run the application, you'll see:
```
========================================
Strategy Pattern Demo - Spring Boot
========================================

Standard Shipping (distance=100): $500
Express Shipping (distance=100): $850
International Shipping (distance=100): $4000

========================================
Available REST API Endpoints:
========================================
GET  /api/shipping/calculate?distance=100
GET  /api/shipping/calculate/{strategy}?distance=100
     (strategy: standard, express, international)
GET  /api/shipping/compare?distance=100
GET  /api/shipping/strategies
POST /api/shipping/strategy?type=expressShipping
========================================
```

## Adding New Strategies

To add a new strategy:

1. Create a class implementing `ShippingCostCalculator`
2. Annotate with `@Component("yourStrategyName")`
3. Implement the required methods
4. Spring will automatically discover and inject it!

Example:
```java
@Component("overnightShipping")
public class OvernightShipping implements ShippingCostCalculator {
    @Override
    public long calculateCost(long distance) {
        return distance * 12 + 100;
    }
    
    @Override
    public String getStrategyName() {
        return "OVERNIGHT";
    }
}
```

No changes needed in `ShippingService` or `ShippingController`!
