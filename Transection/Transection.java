package Transection;

import Car.Car;
import Renter.Renter;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Transection {
    private static final String LOG_FILE = "log.csv";
    private Car car;
    private Renter renter;
    private double totalCost;
    private Date rentDate;
    private Date returnDate;
    private boolean pendingTransaction;
    private boolean insurance;
    private boolean damage;

    public Transection(Car car, Renter renter, double totalCost, Date rentDate, Date returnDate) {
        this.car = car;
        this.renter = renter;
        this.totalCost = totalCost;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.pendingTransaction = true;
        this.insurance = false;
        this.damage = false;
    }

    public double calculateTotalCost(double distance) {
        totalCost = car.calculateRentalCost(distance);
        if(insurance){
            totalCost += car.calculateInsuranceCost();
        }
        if(damage){
            totalCost += car.calculateDamageCost();
        }
        totalCost = renter.applyDiscount(totalCost);
        return totalCost;
    }

    private void saveTranstionLog()
    {
        // Save the transaction in log.csv
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(car.getCarID() + "," + renter.getRenterId() + "," + rentDate + "," + returnDate + "," + totalCost);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void commpleteTransaction(Date returnDate){
        this.returnDate = returnDate;
        pendingTransaction = false;
        saveTranstionLog();
    }

    public boolean ispendingTransaction(){
        return pendingTransaction;
    }

    public Car getCar() {
        return car;
    }

    public Renter getRenter() {
        return renter;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public void setDamage(boolean damage) {
        this.damage = damage;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public Date getRentDate() {
        return rentDate;
    }
}
