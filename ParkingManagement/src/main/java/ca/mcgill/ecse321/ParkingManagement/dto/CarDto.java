package ca.mcgill.ecse321.ParkingManagement.dto;

import ca.mcgill.ecse321.ParkingManagement.model.Size;

public class CarDto {

    // attributes
    private String licencePlate;
    private Size size;

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
    public String getSizeString() {
        if (this.size == Size.Large) {
            return "Large";
        }
        return "Regular";
    }
    
    // setters
    public void setSize(Size size) {
        this.size = size;
    }
}
