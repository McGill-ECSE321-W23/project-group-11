package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.sql.Time;
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
     * creates a temp spot
     *
     * @param 
     * @return
     */
    @Transactional
    public TempSpot createTempSpot(Size size, int duration, Car car, Date date, Time time) {
        TempSpot spot;
        // Null checks for objects
        if (car == null) { 
            // TODO throw exception
            return null;
        }
        // Obtain objects from database to initialize TempSpot
        if (carRepository.existsBylicensePlate(car.getLicensePlate())) { // Check that argument exists in database
            // TODO throw exception
            return null;
        }

        // Check input validity
        if (duration > 48  || duration < 1) {
            // TODO throw exception
            return null;
        }
        
        // Set up spot based on inputs and return
        if (size == Size.Regular)  { 
            spot  = new RegularTempSpot();
        } if  (size == Size.Large) {
            spot  = new LargeTempSpot();
        } else {
            // TODO throw exception
            return null;
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
        int id = 0;
        if (size == Size.Regular) {id = 20;}
        while (true) {
            if (!regularTempSpotRepository.existsById(id) && !largeTempSpotRepository.existsById(id)) {break;} 
            id++;
        } 
        spot.setId(id);

        return spot;
    }


    /**
     * returns a temporary spot from an id
     *
     * @param id of desired spot
     * @return spot corresponding with id
     */
    @Transactional
    public TempSpot getSpot(int id) {
        RegularTempSpot spot = new RegularTempSpot();
        //if (regularTempSpotRepository.existsById(id)) {spot = regularTempSpotRepository.findById(id);} //TODO type error here
        
        return spot;
    }

    /**
     * edits a temporary spot
     *
     * @param 
     * @return 
     */
    @Transactional
    public TempSpot editTempSpot (TempSpot spot) {
        // Null checks

        // Input checks

        // Edit spot

        return spot;
    }


    /**
     * deletes an existing appointment
     *
     * @param
     */
    @Transactional
    public void deleteSpot (TempSpot spot) {
        // Null check

        // Delete associations

        // Delete by id with DAO method

    }

    /**
     * returns tempSpot for a car
     *
     * @param car car with spot reservation
     * @return tempSpot of car
     */
    @Transactional
    public TempSpot getTempSpotReservedByCar(Car car) {
        // Null check
        
        // Find with DAO method
        TempSpot spot = new RegularTempSpot();
        return spot;
    }



    /**
     * returns a list of all temp spots
     *
     * @return list of TempSpots
     */
    @Transactional
    public List<TempSpot> getAllTempSpots(){
        List<TempSpot> allTempSpots = new ArrayList<TempSpot>();
        for (RegularTempSpot regSpot : regularTempSpotRepository.findAll()) {
            allTempSpots.add(regSpot);
        }
        for (LargeTempSpot largeSpot : largeTempSpotRepository.findAll()) {
            allTempSpots.add(largeSpot);
        }

        return allTempSpots;
    }

}