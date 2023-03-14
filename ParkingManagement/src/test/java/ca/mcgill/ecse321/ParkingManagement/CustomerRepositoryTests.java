// package ca.mcgill.ecse321.ParkingManagement;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import ca.mcgill.ecse321.ParkingManagement.dao.CustomerRepository;
// import ca.mcgill.ecse321.ParkingManagement.model.*;

// @SpringBootTest
// public class CustomerRepositoryTests {
// 	@Autowired
// 	private CustomerRepository customerRepository;

// 	@AfterEach
// 	public void clearDatabase() {
// 		customerRepository.deleteAll();
// 	}

// 	@Test
// 	public void testPersistAndLoadAccount() {
// 		// Create Account Object
//         Account account = new Account();
// 		String email = "johnbrown@email.com";
// 		String password = "123123";
// 		account.setEmail(email);
//         account.setPassword(password);

//         Customer customer = new Customer();
//         customer.setAccount(account);

// 		// Save object
// 		customer = customerRepository.save(customer);

// 		// Read object from database
// 		customer = customerRepository.findCustomerByEmail(email);

// 		// Assert that object has correct attributes
// 		assertNotNull(customer);
// 		assertEquals(email, customer.getAccount().getEmail());
//         assertEquals(password, customer.getAccount().getPassword());
// 	}
// }
