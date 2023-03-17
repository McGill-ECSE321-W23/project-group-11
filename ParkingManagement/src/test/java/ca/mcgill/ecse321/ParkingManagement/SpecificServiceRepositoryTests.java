package ca.mcgill.ecse321.ParkingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.ParkingManagement.dao.CarRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ManagerRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ServiceTypeRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.SpecificServiceRepository;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Manager;
import ca.mcgill.ecse321.ParkingManagement.model.ServiceType;
import ca.mcgill.ecse321.ParkingManagement.model.Size;
import ca.mcgill.ecse321.ParkingManagement.model.SpecificService;
import ca.mcgill.ecse321.ParkingManagement.model.SpecificServiceId;

@SpringBootTest
public class SpecificServiceRepositoryTests {

    @Autowired
    private SpecificServiceRepository specificServiceRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void clearDatabase() {
        specificServiceRepository.deleteAll();
        carRepository.deleteAll();
        serviceTypeRepository.deleteAll();
        managerRepository.deleteAll();
    }

    @Test
    public void SpecificServiceTest() {

        // Make manager
        Manager manager = new Manager();
        int tempid = 123;
        manager.setId(tempid);

        // set manager properties
        manager = managerRepository.save(manager);

        // Create a ServiceType
        ServiceType serviceType = new ServiceType();
        serviceType.setName("Oil Change");
        serviceType.setManager(manager);
        serviceType.setCost(5);
        serviceType.setDuration(10);

        // Save ServiceType to repository 
        serviceType = serviceTypeRepository.save(serviceType);
        
        // Make car
        String licensePlate = "NASARULES";
        Size size = Size.Regular;
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setSize(size);

        // Save car to repository 
        car = carRepository.save(car);
        

        // Create a SpecificService
        SpecificService specificService = new SpecificService();
        Date date = Date.valueOf("2023-03-15");
        String employee = "John";
        specificService.setDateAndTime(date);
        specificService.setEmployee(employee);
        specificService.setServiceType(serviceType);
        specificService.setCar(car);

        // Save SpecificService to repository and get its ID (this will test the getter as well)
        specificService = specificServiceRepository.save(specificService);

        // Set variables to null
        specificService = null;
        car = null;
        serviceType = null;
        manager = null;

        // Get SpecificService from repository
        SpecificServiceId specificServiceId = new SpecificServiceId();
        specificServiceId.setServiceType(serviceTypeRepository.findById("Oil Change").orElse(null));
        specificServiceId.setCar(carRepository.findById(licensePlate).orElse(null));
        SpecificService specificServiceFound = specificServiceRepository.findById(specificServiceId).orElse(null);

        // Check SpecificService attributes
        assertNotNull(specificServiceFound);
        assertEquals(date, specificServiceFound.getDate());
        assertEquals(employee, specificServiceFound.getEmployee());
        assertNotNull(specificServiceFound.getServiceType());
        assertNotNull(specificServiceFound.getCar());
    
    }
}