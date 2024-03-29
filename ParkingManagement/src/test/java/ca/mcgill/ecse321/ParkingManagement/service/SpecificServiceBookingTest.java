package ca.mcgill.ecse321.ParkingManagement.service;
// All imports recommended by tutorial notes 2.8.1-2.8.2
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.dto.SpecificServiceDto;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class SpecificServiceBookingTest {

    @Mock
    private ServiceTypeRepository serviceTypeRepository;
    @Mock
    private ManagerRepository managerRepository;
    @Mock
    private CarRepository carRepository;
    @Mock 
    private SpecificServiceRepository specificServiceRepository;

    @InjectMocks
    private SpecificServiceBookingService specificServiceBookingService;

    //general params for a valid Specific Service Booking
    final Date date = Date.valueOf("2023-03-15");
    final LocalTime startTime = LocalTime.now();
    final String employeeName = "Massimo";
    final String licensePlate = "2KX6TF";
    final String serviceTypeName = "Oil Change";
    final List<SpecificService> listOfServices = new ArrayList<>();
    Car car = new Car();
    ServiceType oilChange = new ServiceType();
    SpecificService oilChangeAt3pm = new SpecificService();
    

    @Test
    public void testCreateValidBooking(){
        car.setLicensePlate(licensePlate);
        oilChange.setName(serviceTypeName);
        oilChangeAt3pm.setServiceType(oilChange);
        oilChangeAt3pm.setDate(date);
        when(carRepository.findCarBylicensePlate(any(String.class))).thenReturn(car);
        when(serviceTypeRepository.findServiceTypeByName(any(String.class))).thenReturn(oilChange);
        when(specificServiceRepository.findAll()).thenReturn(listOfServices);
        when(specificServiceRepository.save(any(SpecificService.class))).thenReturn(oilChangeAt3pm);
        SpecificService output = null;
        try{
            
            output = specificServiceBookingService.createBooking(new SpecificServiceDto(0, date, startTime, employeeName, oilChange, car));
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(output);
        assertEquals(oilChangeAt3pm, output);
        assertEquals(oilChange, oilChangeAt3pm.getServiceType());
        assertEquals(date, oilChangeAt3pm.getDate());
    }
    @Test
    public void testCreateBookingWithNullInput(){
        Exception e = assertThrows(Exception.class,
				() -> specificServiceBookingService.createBooking(new SpecificServiceDto(0, null, startTime, employeeName, oilChange, car)));
        assertEquals(e.getMessage(),"Missing information about the booking");
    }
    //could add tests for more null checks for better coverage, but its slightly redundant
    @Test
    public void testCreateBookingWithUnregisteredCar(){
        car.setLicensePlate(licensePlate);
        when(carRepository.findCarBylicensePlate(any(String.class))).thenReturn(null);
        Exception e = assertThrows(Exception.class,
				() -> specificServiceBookingService.createBooking(new SpecificServiceDto(0, date, startTime, employeeName, oilChange, car)));
        assertEquals(e.getMessage(),"Car is not registered");
    }
    @Test
    public void testCreateBookingWithWrongServiceType(){
        car.setLicensePlate(licensePlate);
        oilChange.setName(serviceTypeName);
        when(carRepository.findCarBylicensePlate(any(String.class))).thenReturn(car);
        when(serviceTypeRepository.findServiceTypeByName(any(String.class))).thenReturn(null);
        Exception e = assertThrows(Exception.class,
				() -> specificServiceBookingService.createBooking(new SpecificServiceDto(0, date, startTime, employeeName, oilChange, car)));
        assertEquals(e.getMessage(),"Service Type does not exist");
    }
    @Test
    public void testCreateBookingWithTakenDateTimeAndEmployee(){
        car.setLicensePlate(licensePlate);
        oilChange.setName(serviceTypeName);
        oilChangeAt3pm.setServiceType(oilChange);
        oilChangeAt3pm.setDate(date);
        oilChangeAt3pm.setStartTime(startTime);
        oilChangeAt3pm.setEmployee(employeeName);
        listOfServices.add(oilChangeAt3pm);
        when(carRepository.findCarBylicensePlate(any(String.class))).thenReturn(car);
        when(serviceTypeRepository.findServiceTypeByName(any(String.class))).thenReturn(oilChange);
        when(specificServiceRepository.findAll()).thenReturn(listOfServices);
        Exception e = assertThrows(Exception.class,
				() -> specificServiceBookingService.createBooking(new SpecificServiceDto(0, date, startTime, employeeName, oilChange, car)));
        assertEquals(e.getMessage(),"Booking at this time and date with this employee already exists");
    }
    @Test 
    public void testGetBookingById(){
        when(specificServiceRepository.findSpecificServiceById(any(Integer.class))).thenReturn(oilChangeAt3pm);
        SpecificService output = null;
        try{
            output = specificServiceBookingService.getBookingById(oilChangeAt3pm.getId());
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(output);
        assertEquals(output,oilChangeAt3pm);
    }
    @Test
    public void testGetBookingByInvalidId(){
        when(specificServiceRepository.findSpecificServiceById(any(Integer.class))).thenReturn(null);
        Exception e = assertThrows(Exception.class,
				() -> specificServiceBookingService.getBookingById(oilChangeAt3pm.getId()));
        assertEquals(e.getMessage(),"Booking with given id does not exist");
    }
    @Test
    public void testGetAllBookings(){
        listOfServices.add(oilChangeAt3pm);
        when(specificServiceRepository.findAll()).thenReturn(listOfServices);
        Iterable<SpecificService> output = new ArrayList<>();
        try{
            output = specificServiceBookingService.getAllBookings();  
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(output);
        assertEquals(output,listOfServices);
    }
    @Test
    public void testGetAllBookingByCar(){
        //create a new car that is not associated to the specific service youre looking for
        Car car2 = new Car();
        //create new specific service to add to the list of services 
        SpecificService oilChangeAt4pm = new SpecificService();
        //set cars to the given service types and add cars to the list of services
        car.setLicensePlate(licensePlate);
        car2.setLicensePlate("1D3F5G");
        oilChangeAt3pm.setCar(car);
        oilChangeAt4pm.setCar(car2);
        listOfServices.add(oilChangeAt3pm);
        listOfServices.add(oilChangeAt4pm);
        //create an expected list of services with only the service that the car were looking for is associated to 
        List<SpecificService> expectedListOfServices = new ArrayList<>();
        expectedListOfServices.add(oilChangeAt3pm);
        //mock the method
        when(specificServiceRepository.findAll()).thenReturn(listOfServices);
        Iterable<SpecificService> output = new ArrayList<>();
        try{
            output = specificServiceBookingService.getAllBookingsByCar(car.getLicensePlate());  
        }
        catch(Exception e){
            fail();
        }
        //check to see if the output is what we wanted
        assertNotNull(output);
        assertEquals(output,expectedListOfServices);
    }
    @Test
    public void testDeleteBookingByValidId(){
        when(specificServiceRepository.findSpecificServiceById(any(Integer.class))).thenReturn(oilChangeAt3pm);
        boolean output = false;
        try{
            output = specificServiceBookingService.deleteBookingById(oilChangeAt3pm.getId());
        }
        catch(Exception e){
            fail();
        }
        assertEquals(output,true);
    }
    @Test
    public void testDeleteBookingByInvalidId(){
        when(specificServiceRepository.findSpecificServiceById(any(Integer.class))).thenReturn(null);
        Exception e = assertThrows(Exception.class,
				() -> specificServiceBookingService.deleteBookingById(oilChangeAt3pm.getId()));
        assertEquals(e.getMessage(),"Booking with given id does not exist");
    }
    @Test
    public void testEditBookingByValidId(){
        //set all the things for the booking
        oilChangeAt3pm.setDate(date);
        oilChangeAt3pm.setStartTime(startTime);
        oilChangeAt3pm.setEmployee(employeeName);
        listOfServices.add(oilChangeAt3pm);
        //change the date to something new
        Date newDate = Date.valueOf("2023-03-16");
        SpecificService expectedBooking = new SpecificService(newDate,startTime,employeeName,oilChange,car);
        //mock the service call
        when(specificServiceRepository.findAll()).thenReturn(listOfServices);
        when(specificServiceRepository.findSpecificServiceById(any(Integer.class))).thenReturn(oilChangeAt3pm);
        when(specificServiceRepository.save(any(SpecificService.class))).thenReturn(expectedBooking);
        SpecificService output = null;
        try{
            output = specificServiceBookingService.editBookingById(oilChangeAt3pm.getId(), newDate, startTime, employeeName);
        }
        catch(Exception e){
            fail();
        }
        assertNotNull(output);
        assertEquals(output, expectedBooking);
    }
    @Test
    public void testEditBookingWithNullInput(){
        Exception e = assertThrows(Exception.class,
				() -> specificServiceBookingService.editBookingById(oilChangeAt3pm.getId(),date, null, employeeName));
        assertEquals(e.getMessage(),"Missing information about the booking");
    }
    @Test
    public void testEditBookingByInvalidId(){
        //return a null object when trying to find specific service
        when(specificServiceRepository.findSpecificServiceById(any(Integer.class))).thenReturn(null);
        Exception e = assertThrows(Exception.class,
                () -> specificServiceBookingService.editBookingById(oilChangeAt3pm.getId(),date, startTime, employeeName));
        assertEquals(e.getMessage(),"There is no existing booking with the given id");
    }
    @Test
    public void testEditBookingBySameBooking(){
        oilChangeAt3pm.setDate(date);
        oilChangeAt3pm.setStartTime(startTime);
        oilChangeAt3pm.setEmployee(employeeName);
        listOfServices.add(oilChangeAt3pm);
        when(specificServiceRepository.findSpecificServiceById(any(Integer.class))).thenReturn(oilChangeAt3pm);
        when(specificServiceRepository.findAll()).thenReturn(listOfServices);
        Exception e = assertThrows(Exception.class,
                () -> specificServiceBookingService.editBookingById(oilChangeAt3pm.getId(),date, startTime, employeeName));
        assertEquals(e.getMessage(),"Booking at this time and date already exists");
    }
}