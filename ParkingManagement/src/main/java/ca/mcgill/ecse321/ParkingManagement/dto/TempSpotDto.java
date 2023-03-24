package ca.mcgill.ecse321.ParkingManagement.dto;

import java.sql.Date;
import java.time.LocalTime;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Size;

public class TempSpotDto {
    // attributes
    private int id;
    private int duration;
    private Date date;
    private LocalTime startTime;
    private Size size;
    private int placeNumber;
    // associations
    private Car car;


    // constructors
    public TempSpotDto(int id, int placeNumber, int duration, Date date, LocalTime startTime, Car car, Size size) {
        
        this.id = id;
        this.duration = duration;
        this.date = date;
        this.startTime = startTime;
        this.car = car;
        this.size = size;
        this.placeNumber = placeNumber;
    }


    // getters
    public int getId() {
        return id;
    }
    public int getDuration() {
        return duration;
    }
    public Date getDate() {
        return date;
    }
    public LocalTime getStartTime() {
        return startTime;
    }
    public Car getCar() {
        return car;
    }
    public Size getSize() {
        return size;
    }
    public int getPlaceNumber() {
        return placeNumber;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }



}