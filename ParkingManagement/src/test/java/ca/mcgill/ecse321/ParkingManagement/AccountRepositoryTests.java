// package ca.mcgill.ecse321.ParkingManagement;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
// import ca.mcgill.ecse321.ParkingManagement.dao.ParkingManagementSystemRepository;
// import ca.mcgill.ecse321.ParkingManagement.model.*;



// @SpringBootTest
// class AccountRepositoryTests {

//     @Autowired
//     private ParkingManagementSystemRepository parkingManagementSystemRepository;


// 	@Autowired
// 	private AccountRepository accountRepository;

// 	@AfterEach
// 	public void clearDatabase() {
// 		accountRepository.deleteAll();
//         parkingManagementSystemRepository.deleteAll();

// 	}


// 	@Test
// 	public void testPersistsAndLoadAccount() {

// 		// Make system
// 		ParkingManagementSystem sys = new ParkingManagementSystem();
// 		int id = 20;
// 		sys.setId(id);
// 		parkingManagementSystemRepository.save(sys);

// 		// make Account
//         String username = "anakin.skywalker@gmail.com";
// 		String password = "Padme4eva";

// 		Account account = new Account();
// 		account.setEmail(username);
// 		account.setPassword(password);
// 		account.setParkingManagementSystem(sys);
// 		accountRepository.save(account);



// 		// set variables to null

// 		account = null;
// 		account = accountRepository.findAccountByEmail(username);

// 		// get car from repository

// 		// check that everthing exists as it should
// 		assertEquals(username, account.getEmail());
// 		assertEquals(password, account.getPassword()); // excpected, actual
// 	}
// }