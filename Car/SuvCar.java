package Car;

// • SUV:
// o Features: Spacious, suitable for family trips.
// o Rent Calculation Formula: Base rent + distance traveled cost.
// o Insurable: Yes. (Insurance cost: Fixed percentage of the base rent; Damage
// Cost: Percentage of the base rent)

/**
 * The `SuvCar` class extends `Car` and provides methods for calculating rental and insurance costs,
 * displaying car details, and determining insurability.
 */
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

    public void display(){
        System.out.println("SUV Car");
        System.out.println("Car ID: " + carID);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Rental Fee: " + rentalFee);
        System.out.println("Plate Number: " + plateNumber);
    }

    public void displayDetailsCompact() {
        System.out.printf("\t%-10s %-14s %-14s %-10d %-10.2f %-10s \t SUV Car%n",
            getCarID(), getBrand(), getModel(), getYear(), getRentalFee(), getPlateNumber());
    }
}