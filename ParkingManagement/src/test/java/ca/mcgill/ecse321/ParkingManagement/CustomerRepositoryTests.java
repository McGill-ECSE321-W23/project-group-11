package ca.mcgill.ecse321.ParkingManagement;

import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.CustomerRepository;
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
        Customer Customer = new Customer();
        Customer.setAccount(account);
        Customer.setId(222);
        assertNotNull(Customer.getAccount());

        //save object
        customerRepository.save(Customer);
        email = Customer.getAccount().getEmail();
        
        //read object
        Customer = customerRepository.findCustomerByid(222);

        //Check that object has correct attributes
        assertNotNull(Customer);
        assertNotNull(Customer.getAccount());
        assertEquals(email, Customer.getAccount().getEmail());
        assertEquals(password, Customer.getAccount().getPassword());
    }
}