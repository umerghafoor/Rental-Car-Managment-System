package Car;
// • Compact Car:
// o Features: Basic features, suitable for short-distance travel.
// o Rent Calculation Formula: Base rent + distance traveled cost.
// o Insurable: No.

public class CompactCar extends Car {
    public CompactCar(int carID, String brand, String model, int year, boolean rentalStatus, double rentalFee, String plateNumber) {
        super(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
    }
    public void displayDetails() {
        System.out.printf("\t%-10s %-14s %-14s %-10d %-10s %-10.2f %-10s%n",
            getCarID(), getBrand(), getModel(), getYear(), isRentalStatus(), getRentalFee(), getPlateNumber());
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

}
