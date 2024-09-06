package Renter;

/**
 * The RegularRenter class extends the Renter class and does not apply any discount to the rental fee.
 */
public class RegularRenter extends Renter {
    public RegularRenter(String renterId, String name, String email, String phoneNumber, String address) {
        super(renterId, name, email, phoneNumber, address);
    }

    @Override
    public double applyDiscount(double rentalFee) {
        return rentalFee;
    }

}
