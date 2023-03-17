package ca.mcgill.ecse321.ParkingManagement;

import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.EmployeeRepository;
import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Employee;
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
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    @AfterEach
    public void clearDataBase(){
        employeeRepository.deleteAll();
        accountRepository.deleteAll();
    }

    /**
     * Employee persistence test
     */
    @Test
    public void persistAndLoadEmployee(){
      
        //create account
        String email = "johndoe1955@gmail.com";
        String password = "password";
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);

        //save account
        accountRepository.save(account);

        String targetEmail = account.getEmail();

        //read account
        account = accountRepository.findAccountByEmail(targetEmail);

        //create employee
        String schedule = "MTW";
        Employee employee = new Employee();
        employee.setAccount(account);
        employee.setId(222);
        employee.setSchedule(schedule);
        assertNotNull(employee.getAccount());

        //save object
        employeeRepository.save(employee);
        email = employee.getAccount().getEmail();
        
        //read object
        employee = employeeRepository.findEmployeeById(222);

        //Check that object has correct attributes
        assertNotNull(employee);
        assertNotNull(employee.getAccount());
        assertEquals(schedule, employee.getSchedule());
        assertEquals(email, employee.getAccount().getEmail());
        assertEquals(password, employee.getAccount().getPassword());
    }
}