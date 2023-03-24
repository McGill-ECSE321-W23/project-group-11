package ca.mcgill.ecse321.ParkingManagement.dao;

import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CustomerRepositoryTests {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    @AfterEach
    public void clearDataBase(){
        customerRepository.deleteAll();
        accountRepository.deleteAll();
    }

    /**
     * Customer persistence test
     */
    @Test
    public void persistAndLoadCustomer(){
      
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

        //create Customer
        Customer customer = new Customer();
        customer.setAccount(account);
        assertNotNull(customer.getAccount());

        //save object
        customerRepository.save(customer);
        email = customer.getAccount().getEmail();
        int id = customer.getId();
        
        //read object
        customer = customerRepository.findCustomerByid(id);

        //Check that object has correct attributes
        assertNotNull(customer);
        assertNotNull(customer.getAccount());
        assertEquals(email, customer.getAccount().getEmail());
        assertEquals(password, customer.getAccount().getPassword());
    }
}