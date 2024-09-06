package Car;

// • SUV:
// o Features: Spacious, suitable for family trips.
// o Rent Calculation Formula: Base rent + distance traveled cost.
// o Insurable: Yes. (Insurance cost: Fixed percentage of the base rent; Damage
// Cost: Percentage of the base rent)

public class SuvCar extends Car {
    public SuvCar(int carID, String brand, String model, int year, boolean rentalStatus, double rentalFee, String plateNumber) {
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
        return true;
    }

    public double calculateInsuranceCost() {
        return getRentalFee() * 0.05;
    }

}