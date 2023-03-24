package ca.mcgill.ecse321.ParkingManagement.service;
// All imports recommended by tutorial notes 2.8.1-2.8.2
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    private static final String SERVICE_TYPE_KEY = "TestPerson";

    @Test
    public void testGetServiceTypeByValidName(){
        //mocking the valid ServiceType
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
            //check for a failiure
            fail();
        } 
        //check output
        assertNotNull(output);
        assertEquals(name,output.getName());
        assertEquals(duration,output.getDuration());
        assertEquals(cost,output.getCost());
        assertEquals(manager,output.getManager());
    }
    @Test
    public void testGetServiceTypeByWrongName(){
        final String wrongName = "Not Real";
        when(serviceTypeRepository.findServiceTypeByName(wrongName)).thenReturn(null);
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.getServiceTypeByName(wrongName));
        assertEquals(e.getMessage(),"Service Type does not exist");
    }
    @Test
    public void testGetServiceTypeByNullName(){
        final String nullName = null;
        serviceTypeRepository.findServiceTypeByName(nullName);
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.getServiceTypeByName(nullName));
        assertEquals(e.getMessage(),"Inputted name is null.");
    }
    @Test
    public void testGetAllServiceTypes(){
    
    } 
}