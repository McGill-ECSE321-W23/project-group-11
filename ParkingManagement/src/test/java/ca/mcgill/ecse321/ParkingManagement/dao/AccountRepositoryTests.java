package ca.mcgill.ecse321.ParkingManagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
import ca.mcgill.ecse321.ParkingManagement.model.*;



@SpringBootTest
class AccountRepositoryTests {

    
	@Autowired
	private AccountRepository accountRepository;

	@AfterEach
	@BeforeEach
	public void clearDatabase() {
		accountRepository.deleteAll();
	}


	@Test
	public void testPersistsAndLoadAccount() {

		// make Account
        String username = "anakin.skywalker@gmail.com";
		String password = "Padme4eva";

		Account account = new Account();
		account.setEmail(username);
		account.setPassword(password);
		accountRepository.save(account);


		// set variables to null
		account = null;
		account = accountRepository.findAccountByEmail(username);

		// check that everthing exists as it should
		assertEquals(username, account.getEmail());
		assertEquals(password, account.getPassword()); // excpected, actual
	}
}