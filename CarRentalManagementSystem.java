import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import Car.Car;
import Car.CompactCar;
import Car.LuxuryCar;
import Car.SuvCar;
import Renter.CorporateRenter;
import Renter.FrequentRenter;
import Renter.RegularRenter;
import Renter.Renter;
import Transection.Transection;


public class CarRentalManagementSystem {

    private static final String CAR_FILE_NAME = "cars.csv";
    private static final String RENTER_FILE_NAME = "Renter.csv";
    private static final String TRANSECTION_FILE_NAME = "Transection.csv";
    private List<Car> carList;
    private List<Renter> renterList;
    private List<Transection> transectionsList;

    CarRentalManagementSystem() {
        carList = new ArrayList<Car>();
        renterList = new ArrayList<Renter>();
        transectionsList = new ArrayList<Transection>();
        loadCarsFromFile();
        loadRentersFromFile();
        loadtransectionsFile();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////                                     CAR                                     //////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void addCar() {
        // Ask for car type
        System.out.println("1. Compact Car");
        System.out.println("2. SUV Car");
        System.out.println("3. Luxury Car");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(System.console().readLine());
        Car car = null;
        if (choice == 1) {
            // (int carID, String brand, String model, int year, boolean rentalStatus, double rentalFee, String plateNumber)
            int carID;
            String brand;
            String model;
            int year;
            boolean rentalStatus = false;
            double rentalFee;
            String plateNumber;
            System.out.print("Enter car ID: ");
            carID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter brand: ");
            brand = System.console().readLine();
            System.out.print("Enter model: ");
            model = System.console().readLine();
            System.out.print("Enter year: ");
            year = Integer.parseInt(System.console().readLine());
            System.out.print("Enter rental fee: ");
            rentalFee = Double.parseDouble(System.console().readLine());
            System.out.print("Enter plate number: ");
            plateNumber = System.console().readLine();

            car = new CompactCar(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
        } else if (choice == 2) {
            // (int carID, String brand, String model, int year, boolean rentalStatus, double rentalFee, String plateNumber)
            int carID;
            String brand;
            String model;
            int year;
            boolean rentalStatus = false;
            double rentalFee;
            String plateNumber;
            System.out.print("Enter car ID: ");
            carID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter brand: ");
            brand = System.console().readLine();
            System.out.print("Enter model: ");
            model = System.console().readLine();
            System.out.print("Enter year: ");
            year = Integer.parseInt(System.console().readLine());
            System.out.print("Enter rental fee: ");
            rentalFee = Double.parseDouble(System.console().readLine());
            System.out.print("Enter plate number: ");
            plateNumber = System.console().readLine();

            car = new SuvCar(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
        } else if (choice == 3) {
            // (int carID, String brand, String model, int year, boolean rentalStatus, double rentalFee, String plateNumber)
            int carID;
            String brand;
            String model;
            int year;
            boolean rentalStatus = false;
            double rentalFee;
            String plateNumber;
            System.out.print("Enter car ID: ");
            carID = Integer.parseInt(System.console().readLine());
            System.out.print("Enter brand: ");
            brand = System.console().readLine();
            System.out.print("Enter model: ");
            model = System.console().readLine();
            System.out.print("Enter year: ");
            year = Integer.parseInt(System.console().readLine());
            System.out.print("Enter rental fee: ");
            rentalFee = Double.parseDouble(System.console().readLine());
            System.out.print("Enter plate number: ");
            plateNumber = System.console().readLine();

            car = new LuxuryCar(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
        } else {
            System.out.println("Invalid choice");
            return;
        }
        carList.add(car);
        saveCarsToFile();
    }

    private void loadCarsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CAR_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                int carID = Integer.parseInt(details[0]);
                String brand = details[1];
                String model = details[2];
                int year = Integer.parseInt(details[3]);
                boolean rentalStatus = Boolean.parseBoolean(details[4]);
                double rentalFee = Double.parseDouble(details[5]);
                String plateNumber = details[6];
                String carType = details[7];

                Car car;
                if (carType.equals("CompactCar")) {
                    car = new CompactCar(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
                } else if (carType.equals("SuvCar")) {
                    car = new SuvCar(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
                } else if (carType.equals("LuxuryCar")) {
                    car = new LuxuryCar(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
                }
                else {
                    System.out.println("Invalid car type");
                    return;
                }
                carList.add(car);
            }
        } catch (IOException e) {
            System.out.println("Error loading car data from file: " + e.getMessage());
        }
    }

    private void saveCarsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAR_FILE_NAME))) {
            for (Car car : carList) {
                writer.write(car.getCarID() + "," + car.getBrand() + "," + car.getModel() + "," +
                             car.getYear() + "," + car.isRentalStatus() + "," +
                             car.getRentalFee() + "," + car.getPlateNumber() + ","
                                + car.getClass().getSimpleName()
                             );
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving car data to file: " + e.getMessage());
        }
    }

    private Car returnCar(int carID) {
        for (Car car : carList) {
            if (car.getCarID() == carID) {
                return car;
            }
        }
        return null;
    }

    private void removeCar() {
        System.out.print("Enter car ID: ");
        int carID = Integer.parseInt(System.console().readLine());
        Car car = returnCar(carID);
        if (car == null) {
            System.out.println("Car not found");
            return;
        }
        if(car.isRentalStatus()) {
            System.out.println("Car is rented. Cannot remove");
            return;
        }
        carList.remove(car);
        saveCarsToFile();
    }

    private void displayAvailableCars() {
        System.out.println("\t" + "ID. " + "\t" + "   Brand " + "\t" + "  Model" + "\t" + "         Year" + "\t" + "    Status" + "     " + "Fee" + "\t" + "  Plate Number");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (Car car : carList) {
            if (!car.isRentalStatus()) {
                car.displayDetails();
            }
        }
    }

    private void displayRentedCars()
    {
        System.out.println("\t" + "ID. " + "\t" + "   Brand " + "\t" + "  Model" + "\t" + "         Year" + "\t" + "    Status" + "     " + "Fee" + "\t" + "  Plate Number");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (Car car : carList) {
            if (car.isRentalStatus()) {
                car.displayDetails();
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////                                     Renter                                  //////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void addRenter() {
        // (String renterId, String name, String email, String phoneNumber, String address)
        String renterId;
        String name;
        String email;
        String phoneNumber;
        String address;
        int renterType;
        System.out.print("Enter renter ID: ");
        renterId = System.console().readLine();
        System.out.print("Enter name: ");
        name = System.console().readLine();
        System.out.print("Enter email: ");
        email = System.console().readLine();
        System.out.print("Enter phone number: ");
        phoneNumber = System.console().readLine();
        System.out.print("Enter address: ");
        address = System.console().readLine();
        System.out.println("1. Normal Renter");
        System.out.println("2. Corporate Renter");
        System.out.println("3. Frequent Renter");
        System.out.print("Enter your choice: ");
        renterType = Integer.parseInt(System.console().readLine());
        if (renterType == 1) {
            Renter renter = new RegularRenter(renterId, name, email, phoneNumber, address);
            renterList.add(renter);
        } else if (renterType == 2) {
            Renter renter = new CorporateRenter(renterId, name, email, phoneNumber, address);
            renterList.add(renter);
        } else if (renterType == 3) {
            Renter renter = new FrequentRenter(renterId, name, email, phoneNumber, address);
            renterList.add(renter);
        } else {
            System.out.println("Invalid renter type");
            return;
        }
        saveRentersToFile();
    }

    private void loadRentersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RENTER_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                String renterType = details[0];
                String renterId = details[1];
                String name = details[2];
                String email = details[3];
                String phoneNumber = details[4];
                String address = details[5];
                double totalRentalFee = Double.parseDouble(details[6]);
                List<Car> rentedCars = new ArrayList<Car>();
                for (int i = 7; i < details.length; i++) {
                    Car car = returnCar(Integer.parseInt(details[i]));
                    if (car != null) {
                        rentedCars.add(car);
                    }
                }
                Renter renter;
                if (renterType.equals("RegularRenter")) {
                    renter = new RegularRenter(renterId, name, email, phoneNumber, address);
                } else if (renterType.equals("CorporateRenter")) {
                    renter = new CorporateRenter(renterId, name, email, phoneNumber, address);
                } else if (renterType.equals("FrequentRenter")) {
                    renter = new FrequentRenter(renterId, name, email, phoneNumber, address);
                } else {
                    System.out.println("Invalid renter type");
                    return;
                }
                renter.setTotalRentalFee(totalRentalFee);
                renter.setRentedCars(rentedCars);
                renterList.add(renter);
            }
        } catch (IOException e) {
            System.out.println("Error loading renter data from file: " + e.getMessage());
        }
    }

    private void saveRentersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RENTER_FILE_NAME))) {
            for (Renter renter : renterList) {
                writer.write(renter.getClass().getSimpleName() + "," +
                             renter.getRenterId() + "," + renter.getName() + "," +
                             renter.getEmail() + "," + renter.getPhoneNumber() + "," +
                             renter.getAddress() + "," + renter.getTotalRentalFee()
                              );
                for (Car car : renter.getRentedCars())
                {
                    writer.write(",");
                    writer.write(String.valueOf(car.getCarID()));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving car data to file: " + e.getMessage());
        }
    }

    private Renter returnRenter(String renterId) {
        for (Renter renter : renterList) {
            if (renter.getRenterId().equals(renterId)) {
                return renter;
            }
        }
        return null;
    }

    private void removeRenter() {
        System.out.print("Enter renter ID: ");
        String renterId = System.console().readLine();
        Renter renter = returnRenter(renterId);
        if (renter == null) {
            System.out.println("Renter not found");
            return;
        }
        if(renter.getRentedCars().size() > 0) {
            System.out.println("Renter has rented cars. Cannot remove");
            return;
        }
        renterList.remove(renter);
        saveRentersToFile();
    }

    private void displayRenter() {
        System.out.println("============================= All Renter    =============================");
        for (Renter renter : renterList) {
            renter.displayDetails();
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////                           Rent Transactions:                                //////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void loadtransectionsFile()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSECTION_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                Car car = returnCar(Integer.parseInt(details[0]));
                Renter renter = returnRenter(details[1]);
                double totalCost = Double.parseDouble(details[2]);
                Date rentDate = new Date(Long.parseLong(details[3]));
                Date returnDate = null;
                if (!details[4].equals("null")) {
                    returnDate = new Date(Long.parseLong(details[4]));
                }
                Transection transection = new Transection(car, renter, totalCost, rentDate, returnDate);
                transectionsList.add(transection);
            }
        } catch (IOException e) {
            System.out.println("Error loading transections data from file: " + e.getMessage());
        }
    }

    private void saveTranstionFile()
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSECTION_FILE_NAME))) {
            for (Transection transection : transectionsList) {
                writer.write(transection.getCar().getCarID() + "," + transection.getRenter().getRenterId() + "," +
                             transection.getTotalCost() + "," + transection.getRentDate().getTime() + ","
                             );
                if (transection.getRentDate() != null) {
                    writer.write(transection.getRentDate().getTime() + "");
                } else {
                    writer.write("null");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transections data to file: " + e.getMessage());
        }
    }
// • Rent a car to a renter.
    private boolean rentCar(int carID, String renterId) {
        Car car = returnCar(carID);
        if (car == null) {
            System.out.println("Car not found");
            return false;
        }
        if (car.isRentalStatus()) {
            System.out.println("Car is already rented");
            return false;
        }
        Renter renter = returnRenter(renterId);
        if (renter == null) {
            System.out.println("Renter not found");
            return false;
        }
        car.setRentalStatus(true);
        renter.getRentedCars().add(car);
        Date rentDate = new Date();

        Transection transections = new Transection(car, renter, car.getRentalFee(), rentDate, null);
        transectionsList.add(transections);
        return true;
    }

    private Transection getTransection(Car car, Renter renter) {
        if (transectionsList == null) {
            return null;
        }
        if (transectionsList.size() == 0) {
            return null;
        }
        if (car == null || renter == null) {
            return null;
        }
        for (Transection transections : transectionsList) {
            if (transections.getCar() == car && transections.getRenter() == renter) {
                return transections;
            }
        }
        return null;
    }

    private boolean returnCar(int carID, String renterId, double distance) {
        Car car = returnCar(carID);
        if (distance < 0) {
            System.out.println("Invalid distance");
            return false;
        }
        if (car == null) {
            System.out.println("Car not found");
            return false;
        }
        if (!car.isRentalStatus()) {
            System.out.println("Car is not rented");
            return  false;
        }
        Renter renter = returnRenter(renterId);
        if (renter == null) {
            System.out.println("Renter not found");
            return  false;
        }
        if (!renter.getRentedCars().contains(car)) {
            System.out.println("Renter has not rented this car");
            return false;
        }
        Transection transection = getTransection(car, renter);
        if (transection == null) {
            System.out.println("Transection not found");
            return false;
        }
        transection.calculateTotalCost(distance);
        if (transection.ispendingTransaction()) {
            System.out.println("Transection is pending");
            System.out.println("Transaction amount: " + transection.getTotalCost());
            System.out.print("Do you want to pay? (y/n): ");
            String choice = System.console().readLine();
            if (choice.equals("y")) 
            {
                transection.commpleteTransaction(new Date());
            }
            else
            {
                return false;
            }
        }
        car.setRentalStatus(false);
        renter.getRentedCars().remove(car);
        transectionsList.remove(transection);
        return true;
    }

// • Display rental details.
    private void displayRentalDetails() {
        System.out.println("============================= All Rental Details =============================");
        for (Renter renter : renterList) {
            if (renter.getRentedCars().size() == 0) {
                continue;
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------");
            renter.displayDetails();
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }
// • Calculate and display the total rental cost.
    private void displayTotalRentalCost() {
        System.out.println("Enter Renter ID: ");
        String renterId = System.console().readLine();
        Renter renter = returnRenter(renterId);
        if (renter == null) {
            System.out.println("Renter not found");
            return;
        }
        if (renter.getRentedCars().size() == 0) {
            System.out.println("Renter has not rented any car");
            return;
        }
        renter.displayDetails();
        System.out.println("==================================== Total Rental Cost ====================================");
        for (Car car : renter.getRentedCars()) {
            Transection transection = getTransection(car, renter);
            if (transection != null) {
                System.out.println("-----------------------------------------------------------------------------------------------------------------");
                car.display();
                System.out.println("Total Cost: " + transection.calculateTotalCost(0));
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }
// • Provide an option to add insurance if the rented car is insurable.
// • If insurance is added, calculate and include insurance cost in the total.
// • Calculate and display damage cost based on the car type, and insurance status upon
// return.
// • All rent transactions are stored in CRMS.

    public void start() {
        
        // Menu in Loop
        int choice = 1;
        while (choice != 0) {
            System.out.println("==================== menu =====================");
            System.out.println("1. Add Car");
            System.out.println("2. Remove Car");
            System.out.println("3. Dsiplay Available Car");
            System.out.println("4. Display Rented Car");
            System.out.println("5. Add Renter");
            System.out.println("6. Remove Renter");
            System.out.println("7. Display Renter");
            System.out.println("8. Rent Car");
            System.out.println("9. Return Car");
            System.out.println("10. Display Rental Details");
            System.out.println("11. Display Total Rental Cost");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 1:
                    addCar();
                    break;
                case 2:
                    removeCar();
                    break;
                case 3:
                    displayAvailableCars();
                    break;
                case 4:
                    displayRentedCars();
                    break;
                case 5:
                    addRenter();
                    break;
                case 6:
                    removeRenter();
                    break;
                case 7:
                    displayRenter();
                    break;
                case 8:
                    System.out.print("Enter car ID: ");
                    int carID = Integer.parseInt(System.console().readLine());
                    System.out.print("Enter renter ID: ");
                    String renterId = System.console().readLine();
                    if(rentCar(carID, renterId))
                        System.out.println("Car rented successfully");
                    else
                        System.out.println("Car rent failed");
                    break;
                case 9:
                    System.out.print("Enter car ID: ");
                    carID = Integer.parseInt(System.console().readLine());
                    System.out.print("Enter renter ID: ");
                    renterId = System.console().readLine();
                    System.out.print("Enter distance: ");
                    double distance = Double.parseDouble(System.console().readLine());
                    if(returnCar(carID, renterId, distance))
                        System.out.println("Car returned successfully");
                    else
                        System.out.println("Car return failed");
                    break;
                case 10:
                    displayRentalDetails();
                    break;
                case 11:
                    displayTotalRentalCost();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    saveCarsToFile();
                    saveRentersToFile();
                    saveTranstionFile();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
