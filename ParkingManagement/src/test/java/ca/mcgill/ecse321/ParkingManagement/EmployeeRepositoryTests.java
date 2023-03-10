package ca.mcgill.ecse321.ParkingManagement;

import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.CustomerRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.EmployeeRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ManagerRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ParkingManagementSystemRepository;
import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Employee;
import ca.mcgill.ecse321.ParkingManagement.model.ParkingManagementSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EmployeeRepositoryTests {
    @Autowired
    private ParkingManagementSystemRepository parkingManagementSystemRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    @AfterEach
    public void clearDataBase(){
        employeeRepository.deleteAll();
        accountRepository.deleteAll();
        parkingManagementSystemRepository.deleteAll();
    }

    /**
     * Employee persistence test
     */
    @Test
    public void persistAndLoadEmployee(){
        //create object
        ParkingManagementSystem system = new ParkingManagementSystem();
        
        //save object
        parkingManagementSystemRepository.save(system);

        //create object
        String email = "johndoe1955@gmail.com";
        String password = "password";
        Account account = new Account();
        account.setEmail(email);
        account.setEmail(password);

        //save object
        accountRepository.save(account);
        String targetEmail = account.getEmail();

        //read object
        account = accountRepository.findAccountByEmail(targetEmail);


        //create object
        Employee employee = new Employee();
        employee.setAccount(account);
        String schedule = "MTW";
        employee.setSchedule(schedule);

        //save object
        employeeRepository.save(employee);
        int id = employee.getId();
        

       //read object
       employee = employeeRepository.findEmployeeById(id);

        //Check that object has correct attributes
        assertNotNull(employee);
        assertEquals(id, employee.getId());
        assertEquals(schedule, employee.getSchedule());
        assertEquals(email, employee.getAccount().getEmail());
        assertEquals(password, employee.getAccount().getPassword());
    }

}