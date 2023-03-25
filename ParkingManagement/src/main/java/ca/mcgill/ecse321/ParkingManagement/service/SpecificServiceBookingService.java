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
     * @param date date of the booking
     * @param startTime of the booking
     * @param employee name doing the service 
     * @param car associated with the booking
     * @param serviceType associated with the booking
     * @return a booking for a service
     * @throws Exception
     */
    @Transactional
    public SpecificService createBooking(Date date, LocalTime startTime, String employee, Car car, ServiceType serviceType) throws Exception {
        //check if any of the attributes are null
        if(date == null || startTime == null || employee == null || car == null || serviceType == null){
            Exception e = new Exception("Missing information about the booking");
            throw e;
        }
        //check to see if the car exists
        if(carRepository.findCarBylicensePlate(car.getLicensePlate())==null){
            Exception e = new Exception("Car is not registered");
            throw e;
        }
        //check if service type exists
        if(serviceTypeRepository.findServiceTypeByName(serviceType.getName())==null){
            Exception e = new Exception("Service Type does not exist");
            throw e;
        }
        //check to see if the Date and Time is already taken
        for(SpecificService specificService:specificServiceRepository.findAll()){
            if(specificService.getDate().compareTo(date)!=0 && specificService.getStartTime().compareTo(startTime)!=0 && specificService.getEmployee().equals(employee)){
                Exception e = new Exception("Service Type at this time and date already exists");
                throw e;
            }
        }
        //everything is good lets proceed to the creation of the booking
        SpecificService booking = new SpecificService();
        booking.setDate(date);
        booking.setStartTime(startTime);
        booking.setEmployee(employee);
        booking.setCar(car);
        booking.setServiceType(serviceType);

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
     * @param car the car associated with the booking
     * @return a List of bookings
     * @throws Exception
     */
    @Transactional
    public Iterable<SpecificService> getAllBookingsByCar(Car car){
        List<SpecificService> bookingList = new ArrayList();
        for(SpecificService specificService : specificServiceRepository.findAll()){
            if(specificService.getCar() == car){
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
    public void deleteBookingById(int id) throws Exception{
        if(specificServiceRepository.findSpecificServiceById(id)!=null){
            specificServiceRepository.deleteById(id);
            return;
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
        //check to see if the Date and Time is already taken
        for(SpecificService specificService:specificServiceRepository.findAll()){
            if(specificService.getDate().compareTo(date)!=0 && specificService.getStartTime().compareTo(startTime)!=0 && specificService.getEmployee().equals(employee)){
                Exception e = new Exception("Service Type at this time and date already exists");
                throw e;
            }
        }
        //get the booking you want to edit
        SpecificService booking = specificServiceRepository.findSpecificServiceById(id);
        //set the variables
        booking.setEmployee(employee);
        booking.setDate(date);
        booking.setStartTime(startTime);
        //save object
        return specificServiceRepository.save(booking);
    }
}
