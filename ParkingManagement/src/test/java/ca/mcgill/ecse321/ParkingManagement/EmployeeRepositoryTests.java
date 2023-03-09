package ca.mcgill.ecse321.ParkingManagement;

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
public class EmployeeTest {
    @Autowired
    private ParkingManagementSystem parkingManagementSystemRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @BeforeEach
    @AfterEach
    public void clearDataBase(){
        employeeRepository.deleteAll();
        customerRepository.deleteAll();
        accountRepository.deleteAll();
        parkingManagementSystemRepository.deleteAll();
        managerRepository.deleteAll();
    }

    /**
     * Employee persistence test
     */
    @Test
    public void persistAndLoadEmployee(){
        ParkingManagementSystem system = TestingUtility.initparkingManagementSystemRepository(123);
        parkingManagementSystemRepository.save(system);

        Account account = TestingUtility.initAccount("johndoe1955@gmail.com", "password", system);
        accountRepository.save(account);
        account = null;
        account = accountRepository.findAccountByEmail("johndoe1955@gmail.com");
        Employee employee = TestingUtility.initAdmin(account, system);

        employeeRepository.save(employee);
        employee = null;

        int emId = accountRepository.findAccountByEmail("johndoe1955@gmail.com").getUserRole().getId();
        employee = employeeRepository.findAdminById(emId);
        assertNotNull(employee);
        assertEquals(emId, employee.getId());
    }

}