package ca.mcgill.ecse321.ParkingManagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.ParkingManagement.service.*;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class AccountController {

    @Autowired
    private AccountService acservice;

    @Autowired
    AccountRepository accountRepository;

    /**
     * 
     * Get all accounts.
     * 
     * 
     * @return All accounts
     */

    @GetMapping(value = {"/accounts", "/accounts/"})
    public ResponseEntity<?>  getAllAccounts() {
    List<AccountDto> accountList = new ArrayList<>();
    for (Account account : acservice.getAllAccounts()) {
        accountList.add(convertToDto(account));
    }
    return new ResponseEntity<>(accountList, HttpStatus.OK);   
    }
    
    /**
     * Gets an account
     * 
     * @return account
     */

     @GetMapping(value = {"/accounts/{email}","/accounts/{email}/"})
     public ResponseEntity<?> getAccount(@PathVariable String email) throws Exception {
        try{
            return new ResponseEntity<>(convertToDto(acservice.getAccount(email)),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
     }

     /**
      * Creates an account
      * @param AccountDto to create
      * @return Account created
      */

      @PostMapping(value = {"/accounts", "/accounts/"})
      public ResponseEntity<?> createAccount(@RequestBody AccountDto accountDto) throws Exception {
        try{
            //convert to an Account
            Account account = accountDto.toModel();
            //call service
            account = acservice.createAccount(account.getEmail(), account.getPassword());
            //return dto
            return new ResponseEntity<>(convertToDto(account),HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        
      }
    
    /**
     * deletes an account 
     * @param accountDto to delete
     * @return void
     * 
     */

    @DeleteMapping(value = {"/accounts","/accounts/"})
    public ResponseEntity<?> deleteAccount(@RequestBody AccountDto accountDto) {
        try {
            //convert to Account
            Account account = accountDto.toModel();
            //call service
            acservice.deleteAccount(account.getEmail(), account.getPassword());
            //return
            return new ResponseEntity<>("Account deleted",HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = {"/accounts","/accounts/"})
    public ResponseEntity<?> editAccount(@RequestBody AccountDto accountDto) {
        try{
            Account account = accountRepository.findAccountByEmail(accountDto.getEmail());
            acservice.editAccount(account, accountDto.getEmail(), accountDto.getPassword());
            return new ResponseEntity<>("Account Modified", HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //converts an Account into a dto

    private AccountDto convertToDto(Account account) {
        AccountDto dto = new AccountDto(account.getEmail(), account.getPassword(), account.getLoginStatus());
        return dto;
    }
}
