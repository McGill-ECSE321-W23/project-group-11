package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;
import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.utility.DtoConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.time.LocalDate;
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

    CarService carService = new CarService();

    /**
     * Creates a temporary spot
     *
     * @param duration number of 15 minute intervals requested
     * @param carDto DTO of car booking the spot
     * @param date date of reservation
     * @param time of reservation
     * @return DTO of temporary spot created
     * @throws Exception
     */
    @Transactional
    public TempSpotDto createTempSpot(TempSpotDto tempSpotDto) throws Exception {
        checkTempSpots(); // Refresh to remove expired temp spots
        
        TempSpot spot;
        int duration = tempSpotDto.getDuration();
        CarDto carDto = tempSpotDto.getCarDto();
        Date date = tempSpotDto.getDate();
        LocalTime time = tempSpotDto.getStartTime();

        // Obtain objects from database to initialize TempSpot
        if (!carRepository.existsBylicensePlate(carDto.getLicensePlate())) { // Check if car exists in database
            carService.createCar(carDto.getLicensePlate(), carDto.getSize());
            
        }
        Car car = carRepository.findCarBylicensePlate(carDto.getLicensePlate());

        // Check input validity
        if (duration > 48  || duration < 1) {
            throw new Exception("Inputted durration exceeds bounds of accepted values ([1, 48] intervals of 15 minutes).");
        }
        if ((date.compareTo(Date.valueOf(LocalDate.now())) < 0)) {
            if (time.compareTo(LocalTime.now()) < 0) {
                throw new Exception("Start of attempted booking is in the past.");
            }
        }

        // Set up spot based on inputs and return
        Size size = car.getSize();
        if (size == Size.Regular)  { 
            spot  = new RegularTempSpot();
        } else if  (size == Size.Large) {
            spot  = new LargeTempSpot();
        } else {
            throw new Exception("Car size not recognized.");
        }
        spot.setCar(car);
        spot.setDuration(duration);
        spot.setStartTime(time);
        spot.setDate(date);

        /*
         * set place number
         * check to make sure that place number is not used for another tempSpot
         * large spots have places 1-20
         * regular spots have places 21- 270
         */
        int place = 1;
        if (size == Size.Regular) {
            place = 21;
            while (true) {
                if (!regularTempSpotRepository.existsByPlaceNumber(place)) {
                    spot.setPlaceNumber(place);
                    break;
                } else {
                    place++;
                }
                if (place > 270) {
                    throw new Exception("There are no more regular spots available.");
                }
            }
        } else if (size == Size.Large) {
            while (place <= 20)
                if (!largeTempSpotRepository.existsByPlaceNumber(place)) {
                    spot.setPlaceNumber(place);
                    break;
                } else {
                    place++;
                }
                if (place > 20) {
                    throw new Exception("There are no more large spots available.");
                }
        } else {
            throw new Exception("Size issue.");
        }

        spot.setPlaceNumber(place);
        return DtoConverters.convertToTempSpotDto(spot);
    }

    

    /**
     * Returns a temporary spot from an id
     *
     * @param id of desired spot
     * @return DTO of spot corresponding with id
     * @throws Exception
     */
    @Transactional
    public TempSpotDto getSpotByPlaceNumber(int placeNumber) throws Exception{
        checkTempSpots(); // Refresh to remove expired temp spots

        TempSpot spot;
        if (regularTempSpotRepository.existsByPlaceNumber(placeNumber)) {
            spot = regularTempSpotRepository.findByPlaceNumber(placeNumber); // if someone is using this as an example and is getting an error with findById, add .get() to the end
        }
        else if (largeTempSpotRepository.existsByPlaceNumber(placeNumber)) {
            spot = largeTempSpotRepository.findByPlaceNumber(placeNumber);
        }
        else {
            Exception e = new Exception("No temporary spot currently is reserved with that place number.");
            throw e;
        }
        return DtoConverters.convertToTempSpotDto(spot);
    }

    /**
     * Edits an existing temporary spot with a new number of 15 minute intervals
     *
     * @param spot temporary spot to edit
     * @param duration new number of 15 minute intervals
     * @return temporary spot DTO
     * @throws Exception
     */
    @Transactional
    public TempSpotDto editTempSpot (TempSpotDto spotDto, int duration) throws Exception{
        checkTempSpots(); // Refresh to remove expired temp spots

        // get spot from its repository
        int place = spotDto.getPlaceNumber();
        TempSpot spot = null;
        boolean large = true;
        if (largeTempSpotRepository.existsByPlaceNumber(place)) {spot = largeTempSpotRepository.findByPlaceNumber(place);}
        else if (regularTempSpotRepository.existsByPlaceNumber(place)) {spot = regularTempSpotRepository.findByPlaceNumber(place); large = false;}

        // check for bad inputs
        if (spot == null) {
            throw new Exception("Inputted spot is null.");
        }
        if (duration < 1 || duration > 48) {
            throw new Exception("Inputted duration is out of accepted bounds.");
        }
        if (duration <= spot.getDuration()) {
            throw new Exception("Inputted duration is less than or equal to existing duration.");
        }
        // set duration and save into correct repository
        spot.setDuration(duration);
        
        if (!large) {regularTempSpotRepository.save((RegularTempSpot) spot);}
        else {largeTempSpotRepository.save((LargeTempSpot) spot);}

        return DtoConverters.convertToTempSpotDto(spot);
    }


    /**
     * Deletes an existing appointment
     *
     * @param spot temporary spot to delete
     * @return boolean indicating success of deletion
     * @throws Exception
     */
    @Transactional
    public boolean deleteTempSpot (TempSpotDto spotDto) throws Exception {
        checkTempSpots(); // Refresh to remove expired temp spots

        boolean deleted = false;
        boolean large = true;
        TempSpot spot;
        if (regularTempSpotRepository.existsByPlaceNumber(spotDto.getPlaceNumber())) {
            large = false;
            spot = regularTempSpotRepository.findByPlaceNumber(spotDto.getPlaceNumber());
        } else if (largeTempSpotRepository.existsByPlaceNumber(spotDto.getPlaceNumber())) {
            spot = largeTempSpotRepository.findByPlaceNumber(spotDto.getPlaceNumber());
        } else {
            throw new Exception("Inputted spot does not exist in database.");
        }
        
        // TODO check if this is all that needs to be done
        // Delete by id with DAO method
        if (!large) {
            regularTempSpotRepository.deleteById(spot.getId());
            deleted = true;
        } else {
            largeTempSpotRepository.deleteById(spot.getId());
            deleted = true;
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
    public TempSpotDto getTempSpotReservedByCar(Car car) throws Exception {
        checkTempSpots(); // Refresh to remove expired temp spots

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
        return DtoConverters.convertToTempSpotDto(carSpot);
    }



    /**
     * Returns a list of all temp spots
     *
     * @return list of TempSpots
     */
    @Transactional
    public List<TempSpotDto> getAllTempSpots(){
        checkTempSpots(); // Refresh to remove expired temp spots

        TempSpotDto spotDto;
        List<TempSpotDto> allTempSpots = new ArrayList<TempSpotDto>();
        for (RegularTempSpot regSpot : regularTempSpotRepository.findAll()) {
            spotDto = DtoConverters.convertToTempSpotDto(regSpot);
            allTempSpots.add(spotDto);
        }
        for (LargeTempSpot largeSpot : largeTempSpotRepository.findAll()) {
            spotDto = DtoConverters.convertToTempSpotDto(largeSpot);
            allTempSpots.add(spotDto);
        }
        return allTempSpots;
    }

    /**
     * Checks all temp spots to make sure booking duration has not expired
     * If the booking duration has expired, the bookings are deleted
     */
    @Transactional
    public void checkTempSpots() {
        for (RegularTempSpot regSpot : regularTempSpotRepository.findAll()) {
                if (regSpot.getStartTime().plusMinutes(regSpot.getDuration()*15).compareTo(LocalTime.now()) > 0) { 
                    regularTempSpotRepository.delete(regSpot);
                } 
        }
        for (LargeTempSpot largeSpot : largeTempSpotRepository.findAll()) {
            if (largeSpot.getStartTime().plusMinutes(largeSpot.getDuration()*15).compareTo(LocalTime.now()) > 0) { 
                largeTempSpotRepository.delete(largeSpot);
            } 
        }
    }





}