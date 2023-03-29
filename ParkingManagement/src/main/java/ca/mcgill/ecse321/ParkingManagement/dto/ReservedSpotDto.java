package ca.mcgill.ecse321.ParkingManagement.dto;


public class ReservedSpotDto {
    // attributes
    private int id;
    private int placeNumber;
    private int month;
    private int year;
    // associations
    private CarDto carDto;

    // constructors
    public ReservedSpotDto(int id, int placeNumber, int month, int year, CarDto carDto) {
        
        this.id = id;
        this.placeNumber = placeNumber;
        this.month = month;
        this.year = year;
        this.carDto = carDto;
        this.placeNumber = placeNumber;
    }

    // constructors
    public ReservedSpotDto(int month, int year, CarDto carDto) {
        this.month = month;
        this.year = year;
        this.carDto = carDto;
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
    public CarDto getCarDto() {
        return carDto;
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
    public void setCar(CarDto carDto) {
        this.carDto = carDto;
    }
    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }
}