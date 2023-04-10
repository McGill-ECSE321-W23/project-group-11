package ca.mcgill.ecse321.ParkingManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

@Service
public class SpecificServiceBookingService {

    @Autowired
    SpecificServiceRepository specificServiceRepository;
    @Autowired
    ServiceTypeRepository serviceTypeRepository;
    @Autowired
    CarRepository carRepository;

    /**
     * Creates a Booking
     * @param specificServiceDto specific service dto 
     * @return a booking for a service
     * @throws Exception
     */
    @Transactional
    public SpecificService createBooking(SpecificServiceDto specificServiceDto) throws Exception {
        //check if any of the attributes are null
        if(specificServiceDto.getDate() == null || specificServiceDto.getStartTime() == null || specificServiceDto.getEmployee() == null || specificServiceDto.getCar() == null || specificServiceDto.getServiceType() == null){
            Exception e = new Exception("Missing information about the booking");
            throw e;
        }
        //check to see if the car exists
        if(carRepository.findCarBylicensePlate(specificServiceDto.getCar().getLicensePlate())==null){
            Exception e = new Exception("Car is not registered");
            throw e;
        }
        //check if service type exists
        if(serviceTypeRepository.findServiceTypeByName(specificServiceDto.getServiceType().getName())==null){
            Exception e = new Exception("Service Type does not exist");
            throw e;
        }
        //check to see if the Date and Time is already taken
        for(SpecificService specificServiceAll:specificServiceRepository.findAll()){
            if(specificServiceAll.getDate().equals(specificServiceDto.getDate()) && specificServiceAll.getStartTime().equals(specificServiceDto.getStartTime()) && specificServiceAll.getEmployee().equals(specificServiceDto.getEmployee())){
                Exception e = new Exception("Booking at this time and date with this employee already exists");
                throw e;
            }
        }
        //everything is good lets proceed to the creation of the booking
        SpecificService booking = new SpecificService(specificServiceDto.getDate(),specificServiceDto.getStartTime(),specificServiceDto.getEmployee(),specificServiceDto.getServiceType(),specificServiceDto.getCar());
        return specificServiceRepository.save(booking);
    }
    /**
     * Gets a booking from an id
     * @param id unique id for the booking
     * @return a booking for a service
     * @throws Exception
     */
    @Transactional
    public SpecificService getBookingById(int id) throws Exception {
        if(specificServiceRepository.findSpecificServiceById(id)==null){
            Exception e = new Exception("Booking with given id does not exist");
            throw e;
        }
        else{
            return specificServiceRepository.findSpecificServiceById(id);

        }
    }
    /**
     * Gets all bookings
     * @return a booking for a service
     * @throws Exception
     */
    @Transactional
    public Iterable<SpecificService> getAllBookings(){
        return specificServiceRepository.findAll();
    }
    /**
     * Gets all bookings associated to a car 
     * @param String licence plate of the car associated with the booking
     * @return a List of bookings
     * @throws Exception
     */
    @Transactional
    public Iterable<SpecificService> getAllBookingsByCar(String licencePlate){
        List<SpecificService> bookingList = new ArrayList<>();
        for(SpecificService specificService : specificServiceRepository.findAll()){
            if(specificService.getCar().getLicensePlate().equals(licencePlate)){
                bookingList.add(specificService);
            }
        }
        return bookingList;
    }
    /**
     * Deletes a Booking
     * @param id unique id for the booking
     * @return void
     * @throws Exception
     */
    @Transactional
    public boolean deleteBookingById(int id) throws Exception{
        if(specificServiceRepository.findSpecificServiceById(id)!=null){
            specificServiceRepository.deleteById(id);
            return true;
        }
        else{
            Exception e = new Exception("Booking with given id does not exist");
            throw e;
        }
    }
    /**
     * Edits a Booking
     * @param id unique id for the booking
     * @param date of the booking you want to change to
     * @param startTime of the booking you want to change to
     * @param employee name of the employee you are switching to
     * @return a booking for a service
     * @throws Exception
     */
    @Transactional
    public SpecificService editBookingById(int id, Date date, LocalTime startTime, String employee) throws Exception{
        if(date == null || startTime == null || employee == null){
            Exception e = new Exception("Missing information about the booking");
            throw e;
        }
        //get the booking you want to edit check if its a valid booking
        SpecificService booking = specificServiceRepository.findSpecificServiceById(id);
        if(booking == null){
            Exception e = new Exception("There is no existing booking with the given id");
            throw e;
        }
        //check to see if the Date and Time is already taken
        for(SpecificService specificService:specificServiceRepository.findAll()){
            if(specificService.getDate().equals(date) && specificService.getStartTime().equals(startTime) && specificService.getEmployee().equals(employee)){
                Exception e = new Exception("Booking at this time and date already exists");
                throw e;
            }
        }
        //set the variables
        booking.setEmployee(employee);
        booking.setDate(date);
        booking.setStartTime(startTime);
        //save object
        return specificServiceRepository.save(booking);
    }
}
