# Car Rental System

This project is a Java-based car rental system that demonstrates the principles of Object-Oriented Programming (OOP) by implementing different car types and renter types.

## Table of Contents

1. [OOP Principles in This Project](#oop-principles-in-this-project)
   - [Encapsulation](#encapsulation)
   - [Abstraction](#abstraction)
   - [Inheritance](#inheritance)
   - [Polymorphism](#polymorphism)
   - [Composition and Aggregation](#composition-and-aggregation)
2. [Coding Conventions](#coding-conventions)
3. [Conclusion](#conclusion)

## OOP Principles in This Project

### **Encapsulation**

- All class properties (like `carID`, `brand`, `model`) are private.  
- Public getter and setter methods control access to these properties.

### **Abstraction**

- `Car` and `Renter` are abstract classes, defining common features and methods.  
- Specific types (`CompactCar`, `LuxuryCar`, etc., and `RegularRenter`, `FrequentRenter`, etc.) implement these abstract methods.

### **Inheritance**

- Classes like `CompactCar`, `LuxuryCar`, and `SuvCar` inherit from `Car`.  
- `RegularRenter`, `FrequentRenter`, and `CorporateRenter` inherit from `Renter`.  
- This promotes code reuse and reduces redundancy.

### **Polymorphism**

- Different car types override `Car` methods (`calculateRentalCost()`, `calculateDamageCost()`).  
- Different renter types override `Renter` methods (`applyDiscount()`).

### **Composition and Aggregation**

- **Aggregation**: `Renter` class has a list of `Car` objects, but does not own them.  
- **Composition**: `Renter` class includes properties like `name`, `email`, tightly coupled to its lifecycle.

## Coding Conventions

The project follows the Google Java Style Guide:

- **Naming Conventions**: Classes are named in PascalCase (e.g., `LuxuryCar`), methods and variables in camelCase (e.g., `calculateRentalCost`).
- Code Structure
- Javadoc Comments
- Formatting
- Error Handling

## Conclusion

This project showcases a clear understanding of Object-Oriented Programming principles by implementing a car rental system. Each concept is demonstrated through practical use cases, making the codebase flexible, maintainable, and easy to extend in the future.
