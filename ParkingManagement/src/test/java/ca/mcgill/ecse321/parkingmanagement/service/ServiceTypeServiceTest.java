package ca.mcgill.ecse321.ParkingManagement.service;
// All imports recommended by tutorial notes 2.8.1-2.8.2
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTypeServiceTest {

    @Mock
    private ServiceTypeRepository serviceTypeRepository;
    private ManagerRepository managerRepository;

    @InjectMocks
    private ServiceTypeService serviceTypeService;

    @Test
    public void testGetServiceTypeByValidId(){
        //mocking the valid ServiceType
        final int id = 1;
        final String name = "Oil Change";
        final int duration = 15;
        final int cost = 30;
        final Manager manager = new Manager();
        final ServiceType oilChange = new ServiceType(name,duration,cost,manager);
        when(serviceTypeRepository.findServiceTypeByName(name)).thenReturn(oilChange);

        //call the service
        ServiceType output = null;
        try{
            output = serviceTypeService.getServiceTypeByName(name);
        }
        catch(Exception e){
            System.out.println(e);
        } 
        //check output
        assertNotNull(output);
        assertEquals(name,output.getName());
        assertEquals(duration,output.getDuration());
        assertEquals(cost,output.getCost());
        assertEquals(manager,output.getManager());
    }   
}