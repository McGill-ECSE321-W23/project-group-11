package ca.mcgill.ecse321.ParkingManagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Employee;
import ca.mcgill.ecse321.ParkingManagement.model.Manager;
import ca.mcgill.ecse321.ParkingManagement.model.Size;

@SpringBootTest
public class CarRepositoryTests {

	@Autowired
	private CarRepository carRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private LargeTempSpotRepository largeTempSpotRepository;

    @Autowired
    private RegularTempSpotRepository regTempSpotRepository;

	@BeforeEach
	public void clearDatabaseBefore() {
		carRepository.deleteAll();
        managerRepository.deleteAll();
        employeeRepository.deleteAll();
        accountRepository.deleteAll();
        largeTempSpotRepository.deleteAll();
        regTempSpotRepository.deleteAll();
	}

    @AfterEach
	public void clearDatabaseAfter() {
		carRepository.deleteAll();
        managerRepository.deleteAll();
        employeeRepository.deleteAll();
        accountRepository.deleteAll();
        largeTempSpotRepository.deleteAll();
        regTempSpotRepository.deleteAll();
	}

	@Test
	public void carTest() {

        //create account for manager
        String manageremail = "johndoe1955@gmail.com";
        String managerpassword = "password";
        Account manageraccount = new Account();
        manageraccount.setEmail(manageremail);
        manageraccount.setPassword(managerpassword);

        //save account for manager
        accountRepository.save(manageraccount);

        // Create manager
        Manager manager = new Manager();
        manager.setAccount(manageraccount);
        
        // Save manager
        manager = managerRepository.save(manager);

        //create account for employee
        String employeeemail = "johndoe1955@gmail.com";
        String employeepassword = "password";
        Account employeeaccount = new Account();
        employeeaccount.setEmail(employeeemail);
        employeeaccount.setPassword(employeepassword);

        //save account for employee
        employeeaccount = accountRepository.save(employeeaccount);

        // Create employee
        Employee employee = new Employee();
        employee.setAccount(employeeaccount);

        // Save employee
        employee = employeeRepository.save(employee);
        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(employee);
        int employeeId = employee.getId();

		// Make car
        Car car = new Car();
		String licensePlate = "NASARULES";
		Size size = Size.Regular;
		car.setLicensePlate(licensePlate);
		car.setSize(size);
        car.setManager(manager);
        car.setEmployees(employeeSet);

		// Save car to repository and get it's ID (this will test the getter as well)
        String id = car.getLicensePlate();
        car = carRepository.save(car);
        car = carRepository.findCarBylicensePlate(car.getLicensePlate());


		// Set variables to null
		car = null;

		// Get car from repository
		car = carRepository.findCarBylicensePlate(id);

		// Check that everthing exists as it should
		// Car checks
		assertNotNull(car);
		assertEquals("NASARULES", car.getLicensePlate()); // excpected, actual
		assertEquals(Size.Regular, car.getSize());
        assertEquals(manager.getId(), car.getManager().getId());
		assertEquals(employeeemail, employeeRepository.findById(employeeId).orElse(null).getAccount().getEmail());

	}
}