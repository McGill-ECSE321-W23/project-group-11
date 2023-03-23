package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.dto.AccountDto;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    /**
     * Creates an account
     * 
     * @param email username/id of accoount
     * @param password security password for the account
     * @return account created
     * @throws Exception
     */

     @Transactional
     public Account createAccount(String email, String password) throws Exception {
        Account account;

        //null check for the fields

        if (email == null) {
            Exception e = new Exception("Username cannot be null.");
            throw e;
        }

        if(accountRepository.existsByEmail(email)) {
            Exception e = new Exception("Account already exists under this email.");
            throw e;
        }

        if(password == null) {
            Exception e = new Exception("Password cannot be null.");
            throw e;
        }
        account = new Account();
        account.setEmail(email);
        account.setPassword(password);

        accountRepository.save(account);
        return account;
     }

    /**
     * 
     * Returns an account from a username and password combination
     * @param email username
     * @param password password
     * @return account
     * @throws Exception
     * 
     */

    @Transactional
    public Account getAccount(String email, String password) throws Exception {
        Account account;
        if(accountRepository.existsAccountByEmailAndPassword(email,password)) {
            account = accountRepository.findAccountByEmailAndPassword(email, password);
        }
        else {
            Exception e = new Exception("Couldn't find requested accound with given info.");
            throw e;
        }
     return account;
    }

    /**
     * 
     * Modifying Account service
     * 
     * @param account account
     * @param newEmail new Email wanted
     * @param newPassword new password wanted
     * @return account modified
     * @throws Exception
     * 
     */
    @Transactional
    public Account editAccount(Account account, String newEmail, String newPassword) throws Exception {

        if(account == null) {
            Exception e = new Exception("Cant modify no accounts");
            throw e;
        }
        String email = account.getEmail();
        String password = account.getPassword();

        if(newEmail == null && newPassword == null) {
            Exception e = new Exception("Can't modify an account without changed values");
            throw e;
        }

        accountRepository.deleteAccountByEmailAndPassword(email, password);
        if(newEmail != null) {
            account.setEmail(newEmail);
        }
        if(newPassword != null) {
            account.setPassword(newPassword);
        }
        accountRepository.save(account);
        return account;
    }
    
    /**
     * 
     * Deleting Account
     * 
     * @param account account
     * @param email email
     * @param password password
     * @return deletion ack
     * @throws Exception
     */

     @Transactional
     public Account deleteAccount(String email, String password) throws Exception {
        Account account = new Account();
        accountRepository.deleteAccountByEmailAndPassword(email, password);
        account = accountRepository.findAccountByEmailAndPassword(email, password);

        if (account != null) {
            Exception e = new Exception("Account not deleted.");
            throw e;
        }
        return account;
     }

}
