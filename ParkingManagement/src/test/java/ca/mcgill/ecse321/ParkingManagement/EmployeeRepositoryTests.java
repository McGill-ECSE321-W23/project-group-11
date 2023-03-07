package ca.mcgill.ecse321.ParkingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.ParkingManagement.dao.EmployeeRepository;


import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.ParkingManagementSystem;
import ca.mcgill.ecse321.ParkingManagement.model.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class EmployeeTest {

    @Autowired
    private ParkingManagementSystemRepository parkingManagementRepository;


    @Autowired
    private EmployeeRepository EmployeeRepository;

    /**
     * Employee persistence test
     */
    @Test
    public void testPersistAndLoadEmployee() {
        ParkingManagementSystemRepository pas = TestingUtility.initParkingManagementSystemRepository(1);
        parkingManagementSystemRepository.save(pas);


        Employee emplUser = TestingUtility.initEmployee(pas);

        emplUser.setSchedule("MTWF");

        regularUserRepository.save(emplUser);

        emplUser = null;
        
        


        assertNotNull(emplUser);

        assertEquals("MTWF", emplUser.getSchedule());


    }

    @BeforeEach
    @AfterEach
    public void DeleteDataBase() {

        EmployeeRepository.deleteAll();
        parkingManagementSystemRepository.deleteAll();

    }
}