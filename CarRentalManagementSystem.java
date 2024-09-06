
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

    private boolean addCar() {
        // Ask for car type
        System.out.println("1. Compact Car");
        System.out.println("2. SUV Car");
        System.out.println("3. Luxury Car");
        System.out.print("Enter your choice: ");
        int choice = 0;
        try {
            choice = Integer.parseInt(System.console().readLine());
            if (choice < 1 || choice > 3) {
                System.out.println("Invalid choice");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            return false;
        }

        Car car = null;
        int carID;
        String brand;
        String model;
        int year;
        double rentalFee;
        String plateNumber;
        boolean rentalStatus;


        try {
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
            rentalStatus = false;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input");
            return false;
        }

        for (Car c : carList) {
            if (c.getCarID() == carID) {
                System.out.println("Car with this ID already exists");
                return false;
            }
        }

        switch (choice) {
            case 1:
                car = new CompactCar(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
                break;
            case 2:
                car = new SuvCar(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
                break;
            case 3:
                car = new LuxuryCar(carID, brand, model, year, rentalStatus, rentalFee, plateNumber);
                break;
        }

        carList.add(car);
        saveCarsToFile();
        return true;
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

    private boolean removeCar(int carID) {
        Car car = returnCar(carID);
        if (car == null) {
            System.out.println("Car not found");
            return false;
        }
        if(car.isRentalStatus()) {
            System.out.println("Car is rented. Cannot remove");
            return false;
        }
        carList.remove(car);
        saveCarsToFile();
        return true;
    }

    private void displayCars(boolean rentalStatus) {
        System.out.println("\t" + "ID. " + "\t" + "   Brand " + "\t" + "  Model" + "\t" + "         Year" + "\t"  + "    " + "Fee" + "\t" + "     Plate Number");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        for (Car car : carList) {
            if (car.isRentalStatus() == rentalStatus) {
                car.displayDetailsCompact();
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////                                     Renter                                  //////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////

    private boolean addRenter() {
        // (String renterId, String name, String email, String phoneNumber, String address)
        String renterId;
        String name;
        String email;
        String phoneNumber;
        String address;
        int renterType = -1;

        System.out.print("Enter renter ID: ");
        renterId = System.console().readLine();
        for (Renter renter : renterList) {
            if (renter.getRenterId().equals(renterId)) {
                System.out.println("Renter with this ID already exists");
                return false;
            }
        }
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

        try {
            renterType = Integer.parseInt(System.console().readLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            return false;
        }

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
            return false;
        }
        saveRentersToFile();
        return true;
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

    private boolean removeRenter(String renterId) {
        Renter renter = returnRenter(renterId);
        if (renter == null) {
            System.out.println("Renter not found");
            return false;
        }
        if(renter.getRentedCars().size() > 0) {
            System.out.println("Renter has rented cars. Cannot remove");
            return false;
        }
        renterList.remove(renter);
        saveRentersToFile();
        return true;
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
                boolean insurance = Boolean.parseBoolean(details[5]);
                boolean damage = Boolean.parseBoolean(details[6]);
                Transection transection = new Transection(car, renter, totalCost, rentDate, returnDate);
                transection.setInsurance(insurance);
                transection.setDamage(damage);
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
                writer.write(",");
                if (transection.isInsured()) {
                    writer.write("true");
                } else {
                    writer.write("false");
                }
                writer.write(",");
                if (transection.isDamage()) {
                    writer.write("true");
                } else {
                    writer.write("false");
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
    private void addInsurance(int carID, String renterId) {
        Car car = returnCar(carID);
        if (car == null) {
            System.out.println("Car not found");
            return;
        }
        if (!car.isRentalStatus()) {
            System.out.println("Car is not rented");
            return;
        }
        Renter renter = returnRenter(renterId);
        if (renter == null) {
            System.out.println("Renter not found");
            return;
        }
        if (!renter.getRentedCars().contains(car)) {
            System.out.println("Renter has not rented this car");
            return;
        }
        Transection transection = getTransection(car, renter);
        if (transection == null) {
            System.out.println("Transection not found");
            return;
        }
        if (!car.isInsurable()) {
            System.out.println("Car is not insurable");
            return;
        }
        if(transection.isInsured())
        {
            System.out.println("Car Already has Insurence");
            return;
        }
        if (transection.ispendingTransaction()) {
            System.out.println("Transection is pending");
            System.out.println("Transaction amount: " + transection.getTotalCost());
            System.out.print("Do you want to add insurance? (y/n): ");
            String choice = System.console().readLine();
            if (choice.equals("y")) {
                transection.setInsurance(true);
            }
        }
    }
// • Calculate and display damage cost based on the car type, and insurance status upon return.
    private void damageCar(int carID, String renterId) 
    {
        Car car = returnCar(carID);
        if (car == null) {
            System.out.println("Car not found");
            return;
        }
        if (!car.isRentalStatus()) {
            System.out.println("Car is not rented");
            return;
        }
        Renter renter = returnRenter(renterId);
        if (renter == null) {
            System.out.println("Renter not found");
            return;
        }
        if (!renter.getRentedCars().contains(car)) {
            System.out.println("Renter has not rented this car");
            return;
        }
        Transection transection = getTransection(car, renter);
        if (transection == null) {
            System.out.println("Transection not found");
            return;
        }
        if (transection.isInsured())
        {
            System.out.println("Car Already has Insurence");
            return;
        }
        if (transection.ispendingTransaction()) {
            System.out.println("Transection is pending");
            System.out.println("Transaction amount: " + transection.getTotalCost());
            System.out.print("Do you want to add damage cost? (y/n): ");
            String choice = System.console().readLine();
            if (choice.equals("y")) {
                transection.setDamage(true);
            }
        }
    }

    public void start() {

        int carID = -1;
        String renterId = null;
        
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
            System.out.println("12. Add Insurance");
            System.out.println("13. Damage Car");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            switch (choice) {
                case 1:
                    if (addCar())
                        System.out.println("Car added successfully");
                    else
                        System.out.println("Car add failed");
                    break;
                case 2:
                    System.out.print("Enter car ID: ");
                    try {
                        carID = Integer.parseInt(System.console().readLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid car ID");
                        break;
                    }
                    if (removeCar(carID))
                        System.out.println("Car removed successfully");
                    else
                        System.out.println("Car remove failed");
                    break;
                case 3:
                    displayCars(false);
                    break;
                case 4:
                    displayCars(true);
                    break;
                case 5:
                    if (addRenter())
                        System.out.println("Renter added successfully");
                    else
                        System.out.println("Renter add failed");
                    break;
                case 6:
                    System.out.print("Enter renter ID: ");
                    renterId = System.console().readLine();
                    if (removeRenter(renterId))
                        System.out.println("Renter removed successfully");
                    else
                        System.out.println("Renter remove failed");
                    break;
                case 7:
                    displayRenter();
                    break;
                case 8:
                    try {
                        System.out.print("Enter car ID: ");
                        carID = Integer.parseInt(System.console().readLine());
                        System.out.print("Enter renter ID: ");
                        renterId = System.console().readLine();
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                        break;
                    }
                    if(rentCar(carID, renterId))
                        System.out.println("Car rented successfully");
                    else
                        System.out.println("Car rent failed");
                    break;
                case 9:
                    try {
                        System.out.print("Enter car ID: ");
                        carID = Integer.parseInt(System.console().readLine());
                        System.out.print("Enter renter ID: ");
                        renterId = System.console().readLine();
                        System.out.print("Enter distance: ");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input");
                        break;
                    }
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
                case 12:
                    System.out.print("Enter car ID: ");
                    carID = Integer.parseInt(System.console().readLine());
                    System.out.print("Enter renter ID: ");
                    renterId = System.console().readLine();
                    addInsurance(carID, renterId);
                    break;
                case 13:
                    System.out.print("Enter car ID: ");
                    carID = Integer.parseInt(System.console().readLine());
                    System.out.print("Enter renter ID: ");
                    renterId = System.console().readLine();
                    damageCar(carID, renterId);
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
