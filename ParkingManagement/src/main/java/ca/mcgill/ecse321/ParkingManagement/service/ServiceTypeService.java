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
public class ServiceTypeService {
    
    /**
     * Creates a ServiceType
     * @param name name of service you are creating
     * @param cost cost of the service
     * @param duration duration of the service
     * @param manager manager who is adding the service
     * @return service type created
     * @throws Exception
     */

     @Transactional
     public SpecificService createServiceType(String name, int cost, int duration, Manager manager) throws Exception{
        SpecificService specificService;
        //check if manager is null
        if(manager == null){
            Exception e = new Exception("Inputted manager was null");
            throw e;
        }
        //check if the manager is in the database
        if(managerRepository.existsById(manager.getId())){
            Exception e = new Exception("Manager does not exist");
            throw e;
        }
        

        return specificService;
     }
}
