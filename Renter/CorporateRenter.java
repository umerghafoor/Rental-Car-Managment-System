package Renter;

public class CorporateRenter extends Renter {
    private static final double DISCOUNT = 0.8;//20 percent discount

    public CorporateRenter(String renterId, String name, String email, String phoneNumber, String address) {
        super(renterId, name, email, phoneNumber, address);
    }

    @Override
    public double applyDiscount(double rentalFee) {
        return rentalFee * DISCOUNT;
    }
    
}
