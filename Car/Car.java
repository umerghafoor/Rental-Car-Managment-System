package Car;
/**
 * The `Car` class is an abstract class that represents a generic car with properties like car ID,
 * brand, model, year, rental status, rental fee, and plate number, along with methods for displaying
 * car details and calculating rental, damage, and insurance costs.
 */
public abstract class Car {
    protected int carID;
    protected String brand;
    protected String model;
    protected int year;
    protected boolean rentalStatus;
    protected double rentalFee;
    protected String plateNumber;

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

    public void displayDetailsCompact() {
        System.out.printf("\t%-10s %-14s %-14s %-10d %-10.2f %-10s%n",
            getCarID(), getBrand(), getModel(), getYear(), getRentalFee(), getPlateNumber());
    }

    public abstract double calculateRentalCost(double distance);
    public abstract double calculateDamageCost();
    public abstract boolean isInsurable();
    public abstract double calculateInsuranceCost();
}
