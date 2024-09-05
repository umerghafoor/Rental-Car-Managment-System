package Car;
// Car Types:
// • Properties: Car ID, Brand, Model, Year, Rental Status, Rental Fee, Plate number

public abstract class Car {
    private int carID;
    private String brand;
    private String model;
    private int year;
    private boolean rentalStatus;
    private double rentalFee;
    private String plateNumber;

    public Car(int carID, String brand, String model, int year, boolean rentalStatus, double rentalFee, String plateNumber) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.rentalStatus = rentalStatus;
        this.rentalFee = rentalFee;
        this.plateNumber = plateNumber;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(boolean rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public void setRentalFee(double rentalFee) {
        this.rentalFee = rentalFee;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void display(){
        System.out.println("Car ID: " + carID);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Rental Fee: " + rentalFee);
        System.out.println("Plate Number: " + plateNumber);
    }

    public abstract void displayDetails();
    public abstract double calculateRentalCost(double distance);
    public abstract double calculateDamageCost();
    public abstract boolean isInsurable();
    public abstract double calculateInsuranceCost();
}
