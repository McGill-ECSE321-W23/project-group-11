package ca.mcgill.ecse321.ParkingManagement.service;
// All imports recommended by tutorial notes 2.8.1-2.8.2
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    
    //general params for incomplete Specifific Service Booking
    final Date nullDate = null;
    final LocalTime nullStarTime = null;
    final String nullEmployeeName = null;
    final Car nullCar = null;

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
            output = specificServiceBookingService.createBooking(date, startTime, employeeName, car, oilChange);
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
        
    }
}