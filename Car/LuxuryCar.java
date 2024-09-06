
// • Luxury Car:
// o Features: High-end, suitable for special occasions.
// o Rent Calculation Formula: Base rent + distance traveled cost + luxury tax.
// o Insurable: Yes. (Insurance cost: Fixed percentage of the base rent; Damage
// Cost: Percentage of the total cost including luxury tax)

package Car;

public class LuxuryCar extends Car {
    private static final double INSURANCE_COST = 0.1;
    private static final double DAMAGE_COST = 0.2;
    private static final double LUXURY_TAX = 0.15;

    public LuxuryCar(int carID, String brand, String model, int year, boolean rentalStatus, double rentalFee, String plateNumber) {
        super(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
    }
    
    
    private double calculateLuxuryTax() {
        return getRentalFee() * LUXURY_TAX;
    }

    @Override
    public double calculateRentalCost(double distance) {
        // Base rent + distance traveled cost + luxury tax
        return getRentalFee() + (getRentalFee() * distance) + calculateLuxuryTax();
    }

    @Override
    public double calculateDamageCost() {
        double damageCost = (getRentalFee() * DAMAGE_COST) + calculateLuxuryTax();
        return damageCost;
    }

    @Override
    public boolean isInsurable() {
        return true;
    }

    @Override
    public double calculateInsuranceCost() {
        return getRentalFee() * INSURANCE_COST;
    }

}