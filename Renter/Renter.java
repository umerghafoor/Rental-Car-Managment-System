package Renter;

import java.util.ArrayList;
import java.util.List;
import Car.Car;

// Renter Types:
// • Properties: Renter ID, Name, Email, Rented Cars (List of Cars), Total Rental Fee, phone number, address

/**
 * The `Renter` class represents a renter with properties such as renter ID, name, email, rented cars,
 * total rental fee, phone number, and address, along with methods for renting and returning cars,
 * applying discounts, and displaying details.
 */
public abstract class Renter {
    private String renterId;
    private String name;
    private String email;
    private List<Car> rentedCars;
    private double totalRentalFee;
    private String phoneNumber;
    private String address;

    public Renter(String renterId, String name, String email, String phoneNumber, String address) {
        this.renterId = renterId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.rentedCars = new ArrayList<Car>();
        this.totalRentalFee = 0;
    }

    public void rentCar(Car car) {
        rentedCars.add(car);
        totalRentalFee += car.getRentalFee();
    }

    public void returnCar(Car car) {
        rentedCars.remove(car);
        totalRentalFee -= car.getRentalFee();
    }

    public abstract double applyDiscount(double rentalFee);

    public String getRenterId() {
        return renterId;
    }

    public void setRenterId(String renterId) {
        this.renterId = renterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Car> getRentedCars() {
        return rentedCars;
    }

    public void setRentedCars(List<Car> rentedCars) {
        this.rentedCars = rentedCars;
    }

    public double getTotalRentalFee() {
        return totalRentalFee;
    }

    public void setTotalRentalFee(double totalRentalFee) {
        this.totalRentalFee = totalRentalFee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void displayDetails() {
        System.out.println("Renter ID: " + renterId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Total Rental Fee: " + totalRentalFee);
        System.out.println("Rented Cars: ");
        if (rentedCars.isEmpty()) {
            System.out.println("No cars rented");
        }
        for (Car car : rentedCars) {
            car.displayDetailsCompact();
        }
    }
    
}
