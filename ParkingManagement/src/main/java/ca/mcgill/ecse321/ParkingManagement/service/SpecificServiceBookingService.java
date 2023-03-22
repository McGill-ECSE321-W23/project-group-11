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
     * Creates a ServiceType
     * @param id unique id for the booking
     * @param date date of the booking
     * @param startTime the time of the booking
     * @param employee name of the employee doing the service 
     * @param car the car associated with the booking
     * @param specificService the service type associated with the booking
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
        //everything is good lets proceed to the creation of the booking
        SpecificService booking = new SpecificService();
        booking.setDate(date);
        booking.setStartTime(startTime);
        booking.setEmployee(employee);
        booking.setCar(car);
        booking.setServiceType(serviceType);

        return specificServiceRepository.save(booking);
    }
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

    @Transactional
    public Iterable<SpecificService> getAllBookings(){
        return specificServiceRepository.findAll();
    }

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
}
