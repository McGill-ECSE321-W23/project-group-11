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
public class BookingSpecificService {

    @Autowired
    SpecificServiceRepository specificServiceRepository;
    @Autowired
    ServiceTypeRepository serviceTypeRepository;
    @Autowired
    CarRepository carRepository;

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
    public createSpecificService() throws Exception {
        
    }

}
