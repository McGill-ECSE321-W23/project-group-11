package ca.mcgill.ecse321.ParkingManagement.dto;

import ca.mcgill.ecse321.ParkingManagement.model.Car;

public class ReservedSpotDto {
    // attributes
    private int id;
    private int placeNumber;
    private int month;
    private int year;
    // associations
    private Car car;


    // constructors
    public ReservedSpotDto(int id, int placeNumber, int month, int year, Car car) {
        
        this.id = id;
        this.placeNumber = placeNumber;
        this.month = month;
        this.year = year;
        this.car = car;
        this.placeNumber = placeNumber;
    }


    // getters
    public int getId() {
        return id;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public Car getCar() {
        return car;
    }
    public int getPlaceNumber() {
        return placeNumber;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }
}