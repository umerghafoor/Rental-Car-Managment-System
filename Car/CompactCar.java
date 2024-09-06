package Car;
// • Compact Car:
// o Features: Basic features, suitable for short-distance travel.
// o Rent Calculation Formula: Base rent + distance traveled cost.
// o Insurable: No.

public class CompactCar extends Car {
    public CompactCar(int carID, String brand, String model, int year, boolean rentalStatus, double rentalFee, String plateNumber) {
        super(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
    }
    
    @Override
    public double calculateRentalCost(double distance) {
        // Base rent + distance traveled cost
        return getRentalFee() + (getRentalFee() * distance);
    }
    @Override
    public double calculateDamageCost() {
        double damageCost = getRentalFee() * 0.1;
        return damageCost;
    }
    @Override
    public boolean isInsurable() {
        return false;
    }

    @Override
    public double calculateInsuranceCost() {
        return 0;
    }

    public void display(){
        System.out.println("Compact Car");
        System.out.println("Car ID: " + carID);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Rental Fee: " + rentalFee);
        System.out.println("Plate Number: " + plateNumber);
    }

    public void displayDetailsCompact() {
        System.out.printf("\t%-10s %-14s %-14s %-10d %-10.2f %-10s \t Compact Car%n",
            getCarID(), getBrand(), getModel(), getYear(), getRentalFee(), getPlateNumber());
    }

}
