package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;
import ca.mcgill.ecse321.ParkingManagement.dto.ReservedSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.utility.DtoConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservedParkingService {
    @Autowired
    ReservedSpotRepository reservedSpotRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    CarRepository carRepository;

    /**
     * Creates a reserved spot
     *
     * @param carDto DTO of car booking the spot
     * @param date date of reservation
     * @param year year of reservation
     * @return DTO of reserved spot created
     * @throws Exception
     */
    
    public ReservedSpotDto createReservedSpot(ReservedSpotDto reservedSpotDto) throws Exception {
        checkReservedSpots(); // Refresh to remove expired reserved spots
        
        ReservedSpot spot;
        int year = reservedSpotDto.getYear();
        int month = reservedSpotDto.getMonth();
        CarDto carDto = reservedSpotDto.getCarDto();

        YearMonth currentTime = YearMonth.now();
        YearMonth selectedTime = YearMonth.of(year, month);

        if (selectedTime.isBefore(currentTime)) {
            throw new Exception("Selected time cannot be in the past.");
        }

        // Obtain objects from database to initialize reservedSpot
        if (!carRepository.existsBylicensePlate(carDto.getLicensePlate())) { // Check if car exists in database
            createCar(carDto.getLicensePlate(), carDto.getSize());
            
        }

        if (!carRepository.existsBylicensePlate(carDto.getLicensePlate())) { // Check that argument exists in database
            throw new Exception("Inputted licence plate does not match a car in the database.");
        }

        // Check input validity
        if ( month > 12 ) {
            throw new Exception("Invalid Month");
        }

        Car car = carRepository.findCarBylicensePlate(carDto.getLicensePlate());
        Size size = carDto.getSize();


        // Set up spot based on inputs and return
        if (size == Size.Regular)  { 
            spot  = new ReservedSpot();
        } else if (size == Size.Large)  { 
            throw new Exception("Only regular sized cars can reserve a monthly spot.");
        } else {
            Exception e = new Exception("Car size not recognized.");
            throw e;
        }

        spot.setCar(car);
        spot.setMonth(month);
        spot.setYear(year);

        /*
         * set place number
         * check to make sure that place number is not used for another reservedSpot
         *  spots have places 300-499
         */
        int place = 300;
        while (true) {
            if (!reservedSpotRepository.existsByPlaceNumber(place)) {
                spot.setPlaceNumber(place);
                break;
            }
            else {
                place++;
            }
            if (place > 499) {
                throw new Exception("There are no more monthly spots available.");
            }
        }

        spot.setPlaceNumber(place);
        ReservedSpotDto spotdto = DtoConverters.convertToReservedSpotDto(spot);
        reservedSpotRepository.save(spot);
        return spotdto;
    }


    /**
     * Returns a reserved spot from an id
     *
     * @param id of desired spot
     * @return DTO of spot corresponding with id
     * @throws Exception
     */
    @Transactional
    public ReservedSpotDto getSpotByPlaceNumber(int placeNumber) throws Exception{
        checkReservedSpots(); // Refresh to remove expired reserved spots

        ReservedSpot spot;
        if (reservedSpotRepository.existsByPlaceNumber(placeNumber)) {
            spot = reservedSpotRepository.findByPlaceNumber(placeNumber); // if someone is using this as an example and is getting an error with findById, add .get() to the end
        }
        else {
            Exception e = new Exception("No monthly spot currently is reserved with that place number.");
            throw e;
        }
        return DtoConverters.convertToReservedSpotDto(spot);
    }

    /**
     * Deletes an existing reservation
     *
     * @param i reserved spot to delete
     * @return boolean indicating success of deletion
     * @throws Exception
     */
    @Transactional
    public boolean deleteSpot (ReservedSpotDto spotDto) throws Exception {
        checkReservedSpots(); // Refresh to remove expired reserved spots

        boolean deleted = false;
        ReservedSpot spot;
        if (reservedSpotRepository.existsByPlaceNumber(spotDto.getPlaceNumber())) {
            spot = reservedSpotRepository.findByPlaceNumber(spotDto.getPlaceNumber());
        } else {
            throw new Exception("Inputted spot does not exist in database.");
        }
        
        reservedSpotRepository.deleteById(spot.getId());            
        deleted = true;
        
        return deleted;
    }

    /**
     * Returns reserved spots assigned to a car
     *
     * @param car car with spot reservation
     * @return reservedSpot of car
     * @throws Exception
     */
    @Transactional
    public List<ReservedSpotDto> getReservedSpotsByCar(CarDto cardto) throws Exception {

        ReservedSpotDto spotDto;
        List<ReservedSpotDto> reservedSpots = new ArrayList<ReservedSpotDto>();

        // Null check
        if (cardto == null) {
            Exception e = new Exception("Inputted car is null.");
            throw e;
        }

        // Find with DAO method
        for (ReservedSpot spot : reservedSpotRepository.findAll()) {
            if (spot.getCar().getLicensePlate() == cardto.getLicensePlate()) {
                spotDto = DtoConverters.convertToReservedSpotDto(spot);
                reservedSpots.add(spotDto);            
            }
        }
        
        if (reservedSpots.size()==0) {
            throw new Exception("Inputted car is not accociated with any reserved spots.");
        }

        return reservedSpots;
    }

    /**
     * Returns a list of all reserved spots
     *
     * @return list of ReservedSpots
     */
    @Transactional
    public List<ReservedSpotDto> getAllReservedSpots(){
        checkReservedSpots(); // Refresh to remove expired reserved spots

        ReservedSpotDto spotDto;

        List<ReservedSpotDto> allReservedSpots = new ArrayList<ReservedSpotDto>();
        
            for (ReservedSpot spot : reservedSpotRepository.findAll()) {
            spotDto = DtoConverters.convertToReservedSpotDto(spot);
            allReservedSpots.add(spotDto);
        }

        return allReservedSpots;
    }

    /**
     * Checks all reserved spots to make sure booking duration has not expired
     * If the booking duration has expired, the bookings are deleted
     */
    @Transactional
    public void checkReservedSpots() {

        for (ReservedSpot reservedSpot : reservedSpotRepository.findAll()) {

            YearMonth currentTime = YearMonth.now();
            YearMonth selectedTime = YearMonth.of(reservedSpot.getYear(), reservedSpot.getMonth());
            
            if (selectedTime.isBefore(currentTime)) { 
                reservedSpotRepository.delete(reservedSpot);
            }
        }
    }

    /**
     * Checks if the car has a valid reservedspot for the current month, since they can bet bought for any date in the future.
     * @return boolean true if successfully checked
     * @throws Exception
     */
    @Transactional
    public boolean checkCurrentValidityReservedSpots(CarDto cardto) throws Exception {
        checkReservedSpots(); // Refresh to remove expired reserved spots
        YearMonth currentTime = YearMonth.now();

        for (ReservedSpotDto reservedSpot : getReservedSpotsByCar(cardto)) {

            YearMonth selectedTime = YearMonth.of(reservedSpot.getYear(), reservedSpot.getMonth());
            
            if (selectedTime==currentTime) { 
                return true;
            }
        }
        return false;
    }

    /**
     * TODO This is an anti-pattern that needs to be fixed, code exists in CarService.java
     * Creates a car with a plate number and size
     *
     * @param plateNumber license plate of car
     * @param size Size of car (regular or large)
     * @return DTO of car created
     * @throws Exception
     */
    public CarDto createCar(String plateNumber, Size size) throws Exception{
        Car car = new Car();
        car.setLicensePlate(plateNumber);
        car.setSize(size);
        try {
            carRepository.save(car);
        } catch(Exception e) {
            throw new Exception("Car could not be saved because: " + e.getMessage()+ " ");
        }
        

        return DtoConverters.convertToCarDto(car);
    }
}