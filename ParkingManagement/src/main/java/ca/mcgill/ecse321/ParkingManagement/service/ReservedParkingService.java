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
    @Transactional
    public ReservedSpotDto createReservedSpot(CarDto carDto, int year, int month) throws Exception {

        if ( month > 12 ) {
            throw new Exception("Invalid Month");
        }

        // Obtain objects from database to initialize ReservedSpot
        if (!carRepository.existsBylicensePlate(carDto.getLicensePlate())) { // Check that argument exists in database
            throw new Exception("Inputted licence plate does not match a car in the database.");
        }

        Car car = carRepository.findCarBylicensePlate(carDto.getLicensePlate());
        car.setSize(carDto.getSize());

        // Check input validity 
        YearMonth currentTime = YearMonth.now();
        YearMonth selectedTime = YearMonth.of(year, month);

        if (selectedTime.isBefore(currentTime)) {
            throw new Exception("Selected time cannot be in the past.");
        }

        ReservedSpot spot;
        // Make sure size is  only regular, large cars do not have monthly spots
        Size size = car.getSize();
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
    public boolean deleteSpot (ReservedSpot spot) throws Exception {
        boolean deleted = false;
        if (spot == null) {
            Exception e = new Exception("Inputted spot is not taken.");
            throw e;
        } else{
            // Delete by id with DAO method
            reservedSpotRepository.deleteById(spot.getId());
            deleted = true;
            return deleted;
        }
    }

    /**
     * Returns reserved spot assigned to a car
     *
     * @param car car with spot reservation
     * @return reservedSpot of car
     * @throws Exception
     */
    @Transactional
    public ReservedSpot getReservedSpotReservedByCar(Car car) throws Exception {
        // Null check
        if (car == null) {
            Exception e = new Exception("Inputted car is null.");
            throw e;
        }

        // Find with DAO method
        ReservedSpot carSpot = null;
        for (ReservedSpot spot : reservedSpotRepository.findAll()) {
            if (spot.getCar() == car) {
                carSpot = spot; 
                break;
            }
        }
        
        if (carSpot == null) {
            throw new Exception("Inputted car is not accociated with any reserved spots.");
        }
        return carSpot;
    }



    /**
     * Returns a list of all reserved spots
     *
     * @return list of ReservedSpots
     */
    @Transactional
    public List<ReservedSpotDto> getAllReservedSpots(){
        ReservedSpotDto spotDto;

        List<ReservedSpotDto> allReservedSpots = new ArrayList<ReservedSpotDto>();
        
              for (ReservedSpot Spot : reservedSpotRepository.findAll()) {
            spotDto = DtoConverters.convertToReservedSpotDto(Spot);
            allReservedSpots.add(spotDto);
        }

        return allReservedSpots;
    }

}