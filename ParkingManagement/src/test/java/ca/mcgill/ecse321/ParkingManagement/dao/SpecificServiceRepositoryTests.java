package ca.mcgill.ecse321.ParkingManagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.CarRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ManagerRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ServiceTypeRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.SpecificServiceRepository;
import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Manager;
import ca.mcgill.ecse321.ParkingManagement.model.ServiceType;
import ca.mcgill.ecse321.ParkingManagement.model.Size;
import ca.mcgill.ecse321.ParkingManagement.model.SpecificService;

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

    @Autowired
    private AccountRepository accountRepository;


    @BeforeEach
    @AfterEach
    public void clearDatabase() {
        specificServiceRepository.deleteAll();
        carRepository.deleteAll();
        serviceTypeRepository.deleteAll();
        managerRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    public void SpecificServiceTest() {

        //create account
        String email = "johndoe1955@gmail.com";
        String password = "password";
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);

        //save account
        accountRepository.save(account);

        // Create manager
        Manager manager = new Manager();
        manager.setAccount(account);
        
        // Save manager
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
        Date date = new Date(2023/02/02); // year is 1900-based, month is 0-based
        String employee = "John";
        specificService.setServiceType(serviceType);
        specificService.setCar(car);
        specificService.setDate(date);
        specificService.setStartTime(LocalTime.of(9, 30));
        specificService.setEmployee(employee);

        // Save SpecificService to repository and get its ID (this will test the getter as well)
        specificService = specificServiceRepository.save(specificService);


        // Read from DB by ID
        SpecificService specificServiceFromDB = specificServiceRepository.findSpecificServiceById(specificService.getId());

        // Check SpecificService attributes
        assertNotNull(specificServiceFromDB);
        assertEquals(specificService.getId(), specificServiceFromDB.getId());
        assertEquals(date.toString(), specificServiceFromDB.getDate().toString());
        assertEquals(employee, specificServiceFromDB.getEmployee());
        assertNotNull(specificServiceFromDB.getServiceType());
        assertNotNull(specificServiceFromDB.getCar());
    }
}