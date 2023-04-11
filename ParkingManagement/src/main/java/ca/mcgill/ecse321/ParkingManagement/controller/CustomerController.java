package ca.mcgill.ecse321.ParkingManagement.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.ParkingManagement.service.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

@CrossOrigin(origins = "*")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 
     * get customer
     * 
     * 
     */
    @GetMapping(value = {"/customers","/customers/"})
    public ResponseEntity<?>  getAllcustomers() {
        List<CustomerDto> accountList = new ArrayList<>();
        for (Customer customer : customerService.getAllCustomers()) {
            accountList.add(convertToDto(customer));
        }
        return new ResponseEntity<>(accountList, HttpStatus.OK);   
    }

    /**
     * 
     * @param email
     * @return
     */
    @GetMapping(value = {"/customer/{email}","/customer/{email}/"})
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String email) {
        try{
            Customer customer = customerService.getCustomerByEmail(email);
            return new ResponseEntity<>(convertToDto(customer), HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    /**
     * 
     * @param accountDto
     * @return
     */

    @PostMapping(value = {"/customer", "/customer/"})
    public ResponseEntity<?> createCustomer(@RequestBody AccountDto accountDto) {
        try{
            //convert to an Account
            Account account = accountDto.toModel();
            Customer customer = customerService.createCustomer(account);
            return new ResponseEntity<>(convertToDto(customer), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private CustomerDto convertToDto(Customer customer) {
        CustomerDto dto = new CustomerDto(customer.getId(), customer.getAccount().toString());
        return dto;
    }

    @DeleteMapping(value = {"/customer/{email}", "/customer/{email}"})
    public ResponseEntity<?> deleteCustomer(@PathVariable String email) {
        try{
           Customer customer = customerService.getCustomerByEmail(email);
            customerService.deleteCustomer(customer);
            return new ResponseEntity<>("Customer deleted", HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    
    
}
