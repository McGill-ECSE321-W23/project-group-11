package ca.mcgill.ecse321.ParkingManagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.ParkingManagement.model.ServiceType;
import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Manager;


@SpringBootTest
public class ServiceTypeRepositoryTests {

	@Autowired
	private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private ManagerRepository managerRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
 
	@AfterEach
    @BeforeEach
	public void clearDatabase() {
		serviceTypeRepository.deleteAll();
        managerRepository.deleteAll();
        accountRepository.deleteAll();
	}

	@Test
	public void ServiceTypeTest() {
		
        //create account
        String email = "johndoe1955@gmail.com";
        String password = "password";
        boolean loginStatus = false;
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setloginStatus(loginStatus);

        //save account
        accountRepository.save(account);

        // Create manager
        Manager manager = new Manager();
        manager.setAccount(account);
        
        // Save manager
        manager = managerRepository.save(manager);

        //make a servicetype
	    String name = "oilchange";   //make service type name
		int duration = 30; //set duration
        int cost = 20; //set cost
        ServiceType serviceType = new ServiceType();
        serviceType.setCost(cost);
        serviceType.setDuration(duration);
        serviceType.setName(name);
        serviceType.setManager(manager);
        serviceTypeRepository.save(serviceType);  //save it in repo

        //set everything to null
		serviceType = null;

		// Get spot from repository
        serviceType = serviceTypeRepository.findServiceTypeByName(name);

		//check if there is a serviceType
		assertNotNull(serviceType);
        //check if the name is the same as the one you saved
		assertEquals(name, serviceType.getName());
        assertEquals(cost, serviceType.getCost());
        assertEquals(duration, serviceType.getDuration());
        //check if theres a manager
        assertNotNull(serviceType.getManager());
        //check if licence plat is the same as the one you saved
		assertEquals(manager.getId(), serviceType.getManager().getId());
	}
}