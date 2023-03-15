package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public TempSpot createTempSpot() {
        // Check for null arguments
        

        // Check that arguments are in database
        

        // Set up spot based on inputs and return
        // TODO check if regular or large with if statement
        TempSpot spot  = new RegularTempSpot();
        return spot;
    }


    /**
     * returns a temporary spot from an id
     *
     * @param id of desired spot
     * @return spot corresponding with id
     */
    @Transactional
    public TempSpot getSpot(Long id) {
        // Check if this is a large or regular spot
        // Find by ID with DAO method
        
        LargeTempSpot spot  = new LargeTempSpot();
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