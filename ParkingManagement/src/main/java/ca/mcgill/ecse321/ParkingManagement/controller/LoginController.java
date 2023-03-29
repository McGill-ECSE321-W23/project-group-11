package ca.mcgill.ecse321.ParkingManagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ca.mcgill.ecse321.ParkingManagement.service.*;
import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {
    
    @Autowired
	private LoginService service;

    @Autowired
    AccountRepository accountRepository;


    /**
     * Log in a user
     * @param account accountDto which is to be logged in 
     * @return response with message or error
     */
    @PostMapping(value = {"/login","/login/"})
    public ResponseEntity<?> login(@RequestBody AccountDto account) {
        try {
            service.loginAccount(account);
            return new ResponseEntity<>("Login Successful", HttpStatus.OK);
        } catch (Exception e) {
            if (e.getMessage().equals("Account cannot be null.")) {
                return new ResponseEntity<>("Account was null.", HttpStatus.BAD_REQUEST); 
            }

            if (e.getMessage().equals("Account is already logged in.")) {
                return new ResponseEntity<>("Account is already logged in.", HttpStatus.BAD_REQUEST); 
            }

            if (e.getMessage().equals("Invalid password.")) {
                return new ResponseEntity<>("Invalid password.", HttpStatus.BAD_REQUEST); 
            }

            return new ResponseEntity<>("No account with that email exists.", HttpStatus.BAD_REQUEST);
        }
    }



    /**
     * Log out a user
     * @param account accountDto which is to be logged out 
     * @return response with message or error
     */
    @PostMapping(value = {"/logout","/logout/"})
    public ResponseEntity<?> logout(@RequestBody AccountDto account) {
        try {
            service.logoutAccount(account);
            return new ResponseEntity<>("Logout Successful",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
