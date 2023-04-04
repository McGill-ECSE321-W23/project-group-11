package ca.mcgill.ecse321.ParkingManagement.dto;

import ca.mcgill.ecse321.ParkingManagement.model.Customer;
import ca.mcgill.ecse321.ParkingManagement.model.Size;

public class CarDto {

    // attributes
    private String licencePlate;
    private Size size;

    // associations
    private Customer customer;

    // constructors
    public CarDto(String licensePlate) {
        this.licencePlate = licensePlate;
    }
    public CarDto(String licensePlate, Size size) {
        this.licencePlate = licensePlate;
        this.size = size;
    }

    // getters
    public String getLicensePlate() {
        return this.licencePlate;
    }
    public Size getSize() {
        return this.size;
    }
    public Customer getCustomer() {
        return this.customer;
    }
    
    // setters
    public void setSize(Size size) {
        this.size = size;
    }
    public void setPlaceNumber(String licensePlate) {
        this.licencePlate = licensePlate;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
