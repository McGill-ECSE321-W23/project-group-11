package ca.mcgill.ecse321.ParkingManagement.service;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public List<Customer> getAllCustomers() {
        return toList(customerRepository.findAll());
    }

    
    @Transactional
    public Customer createCustomer(Account account) throws Exception {
        if(account == null) {
            Exception e = new Exception("Can't create a customer with null account.");
            throw e;
        }
        if(customerRepository.findCustomerByAccount(account)!=null){
            Exception e = new Exception("Customer already exists.");
            throw e;
        }
        Customer customer = new Customer();
        customer.setAccount(account);
        return customerRepository.save(customer);
    }


    @Transactional
    public Customer getCustomerByEmail(String email) throws Exception {
        if(email == null || email == "") {
            Exception e = new Exception("Can't find customer with empty email.");
            throw e;   
        }
        Account account = accountRepository.findAccountByEmail(email);
        if(account == null) {
            Exception e = new Exception("No account with this email.");
            throw e;
        }
        Customer customer = customerRepository.findCustomerByAccount(account);
        if(customer == null) {
            Exception e = new Exception("No customer with this account.");
            throw e;
        }
        return customer;
    }

    @Transactional
    public Customer deleteCustomer(Customer customer) throws Exception {
        Customer customer_dummy = null;
        if(customer == null) {
            Exception e = new Exception("Cant delete a customer without an account");
            throw e;
        }
        customerRepository.deleteCustomerByAccount(customer.getAccount());
        customer_dummy = customerRepository.findCustomerByAccount(customer.getAccount());
        if (customer_dummy != null) {
            Exception e = new Exception("Manager not deleted");
            throw e;
        }
        return customer_dummy;
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}

