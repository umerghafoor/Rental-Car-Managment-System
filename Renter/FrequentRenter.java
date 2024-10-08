package Renter;

/**
 * The `FrequentRenter` class extends `Renter` and applies a 10 percent discount to the rental fee.
 */
public class FrequentRenter extends Renter {
    private static final double DISCOUNT = 0.9;//10 percent discount

    public FrequentRenter(String renterId, String name, String email, String phoneNumber, String address) {
        super(renterId, name, email, phoneNumber, address);
    }

    @Override
    public double applyDiscount(double rentalFee) {
        return rentalFee * DISCOUNT;
    }
    
}
