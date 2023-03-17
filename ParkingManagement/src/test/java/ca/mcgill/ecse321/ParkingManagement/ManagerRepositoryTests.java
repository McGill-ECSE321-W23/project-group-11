package ca.mcgill.ecse321.ParkingManagement;

import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ManagerRepository;
import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Manager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ManagerRepositoryTests {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @BeforeEach
    @AfterEach
    public void clearDataBase(){
        managerRepository.deleteAll();
        accountRepository.deleteAll();
    }

    /**
     * Manager persistence test
     */
    @Test
    public void persistAndLoadManager(){
      
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

        //create Manager
        Manager Manager = new Manager();
        Manager.setAccount(account);
        Manager.setId(222);
        assertNotNull(Manager.getAccount());

        //save Manager
        managerRepository.save(Manager);
        email = Manager.getAccount().getEmail();
        
        //read Manager
        Manager = managerRepository.findManagerByid(222);

        //Check that Manager has correct attributes
        assertNotNull(Manager);
        assertNotNull(Manager.getAccount());
        assertEquals(email, Manager.getAccount().getEmail());
        assertEquals(password, Manager.getAccount().getPassword());
    }
}