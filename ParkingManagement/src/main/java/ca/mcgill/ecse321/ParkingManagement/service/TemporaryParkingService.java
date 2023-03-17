package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TemporaryParkingService {
    @Autowired
    RegularTempSpotRepository regularTempSpotRepository;
    @Autowired
    LargeTempSpotRepository largeTempSpotRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    CarRepository carRepository;

    /**
     * Creates a temporary spot
     *
     * @param size size of parking spot (Regular or Large)
     * @param duration number of 15 minute intervals requested
     * @param date date of reservation
     * @param time of reservation
     * @return temporary spot created
     * @throws Exception
     */
    @Transactional
    public TempSpot createTempSpot(Size size, int duration, Car car, Date date, LocalTime time) throws Exception {
        TempSpot spot;
        // Null checks for objects
        if (car == null) { 
            Exception e = new Exception("Inputted car was null.");
            throw e;
        }
        // Obtain objects from database to initialize TempSpot
        if (carRepository.existsBylicensePlate(car.getLicensePlate())) { // Check that argument exists in database
            Exception e = new Exception("Inputted car does not have a license plate that exists in the database.");
            throw e;
        }

        // Check input validity
        if (duration > 48  || duration < 1) {
            Exception e = new Exception("Inputted durration exceeds bounds of accepted values ([1, 48] intervals of 15 minutes).");
            throw e;
        }
        
        // Set up spot based on inputs and return
        if (size == Size.Regular)  { 
            spot  = new RegularTempSpot();
        } if  (size == Size.Large) {
            spot  = new LargeTempSpot();
        } else {
            Exception e = new Exception("Size not recognized.");
            throw e;
        }
        
        spot.setCar(car);
        spot.setDuration(duration);
        spot.setStartTime(time);
        spot.setDate(date);

        /*
         * set id
         * check to make sure that id is not used for another tempSpot
         * large spots have ids 1-20
         * regular spots have ids 21- 270
         */
        int id = 1;
        if (size == Size.Regular) {
            id = 21;
            while (true) {
                if (!regularTempSpotRepository.existsById(id)) {break;} 
                id++;
                if (id > 270) {
                    Exception e = new Exception("There are no more regular spots available.");
                    throw e;
                }
            }
        }
        if (size == Size.Large) {
            while (id <= 20)
                if (!largeTempSpotRepository.existsById(id)) {break;} 
                id++;
                if (id > 20) {
                    Exception e = new Exception("There are no more large spots available.");
                    throw e;
                }
        } 
        spot.setId(id);

        return spot;
    }


    /**
     * Returns a temporary spot from an id
     *
     * @param id of desired spot
     * @return spot corresponding with id
     * @throws Exception
     */
    @Transactional
    public TempSpot getSpot(int id) throws Exception{
        TempSpot spot;
        if (regularTempSpotRepository.existsById(id)) {spot = regularTempSpotRepository.findById(id).get();}
        else if (largeTempSpotRepository.existsById(id)) {spot = largeTempSpotRepository.findById(id).get();}
        else {
            Exception e = new Exception("No temporary spot currently is reserved with that id.");
            throw e;
        }
        return spot;
    }

    /**
     * Edits an existing temporary spot with a new number of 15 minute intervals
     *
     * @param spot temporary spot to edit
     * @param duration new number of 15 minute intervals
     * @return temporary spot editted
     * @throws Exception
     */
    @Transactional
    public TempSpot editTempSpot (TempSpot spot, int duration) throws Exception{
        if (spot == null) {
            Exception e = new Exception("Inputted spot is null.");
            throw e;
        }
        if (duration < 1 || duration > 48) {
            Exception e = new Exception("Inputted duration is out of accepted bounds.");
            throw e;
        }
        if (duration == spot.getDuration()) {
            Exception e = new Exception("Inputted duration is not different than existing duration.");
            throw e;
        }
        spot.setDuration(duration);

        return spot;
    }


    /**
     * Deletes an existing appointment
     *
     * @param spot temporary spot to delete
     * @return boolean indicating success of deletion
     * @throws Exception
     */
    @Transactional
    public boolean deleteSpot (TempSpot spot) throws Exception {
        boolean deleted = false;
        if (spot == null) {
            Exception e = new Exception("Inputted spot is null.");
            throw e;
        }

        // Delete associations 
        
        // TODO check if this is all that needs to be done
        // Delete by id with DAO method
        if (spot instanceof RegularTempSpot) {
            regularTempSpotRepository.deleteById(spot.getId());
            deleted = true;
        } else if (spot instanceof LargeTempSpot) {
            largeTempSpotRepository.deleteById(spot.getId());
            deleted = true;
        } else {
            Exception e = new Exception("Inputted spot is has unrecognizable size and could not be deleted.");
            throw e;
        }
        return deleted;
    }

    /**
     * Returns temporary spot assigned to a car
     *
     * @param car car with spot reservation
     * @return tempSpot of car
     * @throws Exception
     */
    @Transactional
    public TempSpot getTempSpotReservedByCar(Car car) throws Exception {
        // Null check
        if (car == null) {
            Exception e = new Exception("Inputted car is null.");
            throw e;
        }

        // Find with DAO method
        TempSpot carSpot = null;
        for (TempSpot spot : largeTempSpotRepository.findAll()) {
            if (spot.getCar() == car) {carSpot = spot; break;}
        }
        for (TempSpot spot : regularTempSpotRepository.findAll()) {
            if (spot.getCar() == car) {carSpot = spot; break;}
        }

        if (carSpot == null) {
            Exception e = new Exception("Inputted car is not accociated with any temporary spots.");
            throw e;
        }
        return carSpot;
    }



    /**
     * Returns a list of all temp spots
     *
     * @return list of TempSpots
     */
    @Transactional
    public List<TempSpotDto> getAllTempSpots(){
        TempSpotDto spotDto;
        List<TempSpotDto> allTempSpots = new ArrayList<TempSpotDto>();
        for (RegularTempSpot regSpot : regularTempSpotRepository.findAll()) {
            spotDto = convertToDto(regSpot);
            allTempSpots.add(spotDto);
        }
        for (LargeTempSpot largeSpot : largeTempSpotRepository.findAll()) {
            spotDto = convertToDto(largeSpot);
            allTempSpots.add(spotDto);
        }
        return allTempSpots;
    }

    private TempSpotDto convertToDto(TempSpot spot) {
        TempSpotDto dto = new TempSpotDto(spot.getId(), spot.getDuration(), spot.getDate(), spot.getStartTime(), spot.getCar());
        return dto;
    }





}