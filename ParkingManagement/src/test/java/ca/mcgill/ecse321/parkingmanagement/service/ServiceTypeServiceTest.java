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

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTypeServiceTest {

    @Mock
    private ServiceTypeRepository serviceTypeRepository;
    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private ServiceTypeService serviceTypeService;

    //general shared attributes for a valid ServiceType 
    final String name = "Oil Change";
    final int duration = 15;
    final int cost = 30;
    final Manager manager = new Manager();
    final int id = manager.getId();
    final ServiceType oilChange = new ServiceType(name,duration,cost,manager);

    //general shared attributes for an invalid ServiceType

    @Test
    public void testGetServiceTypeByValidName(){
        //mocking the valid ServiceType
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
        serviceTypeRepository.findServiceTypeByName(null);
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.getServiceTypeByName(null));
        assertEquals(e.getMessage(),"Inputted name is null.");
    }
    @Test
    public void testGetAllServiceTypes(){
        final String name2 ="Tire Change";
        final ServiceType tireChange = new ServiceType(name2,duration,cost,manager);
        List<ServiceType> serviceTypeList = new ArrayList<>();
        serviceTypeList.add(oilChange);
        serviceTypeList.add(tireChange);
        when(serviceTypeRepository.findAll()).thenReturn(serviceTypeList);
        Iterable<ServiceType> output = null;
        try{
            output = serviceTypeService.getAllServiceTypes();
        }
        catch(Exception e){
            //check for a failiure
            fail();
        }
        assertNotNull(output);
        assertEquals(output,serviceTypeList);
    }
    @Test
    public void testCreateValidServiceType(){
        final int id= manager.getId();
        when(managerRepository.findManagerByid(id)).thenReturn(manager);
        when(serviceTypeRepository.findServiceTypeByName(name)).thenReturn(null);
        when(serviceTypeRepository.save(any(ServiceType.class))).thenReturn(oilChange);
        ServiceType output = null;
        try{
            output = serviceTypeService.createServiceType(name,cost,duration,manager);
        }
        catch(Exception e){
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
    public void testCreateServiceTypeWithNullName(){
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.createServiceType(null,cost,duration,manager));
        assertEquals(e.getMessage(),"Missing information about the service type, must have a non null name and manager");
    }
    @Test
    public void testCreateServiceTypeWithNullManager(){
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.createServiceType(name,cost,duration,null));
        assertEquals(e.getMessage(),"Missing information about the service type, must have a non null name and manager");
    }
    @Test
    public void testCreateServiceTypeWithFakeManager(){
        when(managerRepository.findManagerByid(id)).thenReturn(null);
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.createServiceType(name,cost,duration,manager));
        assertEquals(e.getMessage(),"Manager does not exist");
    }
    @Test
    public void testCreateServiceTypeWithExistingServiceType(){
        when(managerRepository.findManagerByid(id)).thenReturn(manager);
        when(serviceTypeRepository.findServiceTypeByName(name)).thenReturn(oilChange);
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.createServiceType(name,cost,duration,manager));
        assertEquals(e.getMessage(),"Service Type already exists");
    }
    @Test
    public void testRemoveValidServiceType(){
        final int id= manager.getId();
        when(managerRepository.findManagerByid(id)).thenReturn(manager);
        when(serviceTypeRepository.findServiceTypeByName(name)).thenReturn(oilChange);
        boolean output = false;
        try{
            output = serviceTypeService.removeServiceType(oilChange);
        }
        catch(Exception e){
            fail();
        } 
        //check output
        assertNotNull(output);
        assertTrue(output);
    }
    @Test
    public void testRemoveServiceTypeWithNullServiceType(){
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.removeServiceType(null));
        assertEquals(e.getMessage(),"Service Type is null");
    }
    @Test
    public void testRemoveServiceTypeWithFakeManager(){
        when(managerRepository.findManagerByid(id)).thenReturn(null);
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.removeServiceType(oilChange));
        assertEquals(e.getMessage(),"Manager does not exist");
    }
    @Test
    public void testRemoveServiceTypeWithFakeServiceType(){
        when(managerRepository.findManagerByid(id)).thenReturn(manager);
        when(serviceTypeRepository.findServiceTypeByName(name)).thenReturn(null);
        Exception e = assertThrows(Exception.class,
				() -> serviceTypeService.removeServiceType(oilChange));
        assertEquals(e.getMessage(),"Inputted service type doesn not exist.");
    }
}