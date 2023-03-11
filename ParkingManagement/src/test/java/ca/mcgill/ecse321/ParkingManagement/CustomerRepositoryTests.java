package ca.mcgill.ecse321.ParkingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.CustomerRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ParkingManagementSystemRepository;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@SpringBootTest
public class CustomerRepositoryTests {
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
    private ParkingManagementSystemRepository parkingManagementSystemRepository;
    @Autowired
    private AccountRepository accountRepository;

	@AfterEach
	public void clearDatabase() {
		customerRepository.deleteAll();
		accountRepository.deleteAll();
        parkingManagementSystemRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadAccount() {
		// Create Account Object
        Account account = new Account();
		String email = "johnbrown@email.com";
		String password = "123123";
		account.setEmail(email);
        account.setPassword(password);
		account.setCar(null);
		

        Customer customer = new Customer();
        customer.setAccount(account);
		customer.setId(0);

		// Save object
		customer = customerRepository.save(customer);
		int id = customer.getId();

		// Read object from database
		customer = customerRepository.findCustomerById(id);

		// Assert that object has correct attributes
		assertNotNull(customer);
		assertEquals(email, customer.getAccount().getEmail());
        assertEquals(password, customer.getAccount().getPassword());
		assertEquals(id, customer.getId());
	}
}
