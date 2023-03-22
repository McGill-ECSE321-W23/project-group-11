package ca.mcgill.ecse321.ParkingManagement.dao;

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
        Manager manager = new Manager();
        manager.setAccount(account);
        assertNotNull(manager.getAccount());

        //save Manager
        manager = managerRepository.save(manager);
        email = manager.getAccount().getEmail();
        
        //read Manager
        Manager managerfromdb = managerRepository.findManagerByid(manager.getId());

        //Check that Manager has correct attributes
        assertNotNull(manager);
        assertNotNull(manager.getAccount());
        assertEquals(email, manager.getAccount().getEmail());
        assertEquals(manager.getId(), managerfromdb.getId());
    }
}