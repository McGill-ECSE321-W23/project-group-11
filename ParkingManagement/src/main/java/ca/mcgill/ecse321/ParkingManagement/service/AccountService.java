package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
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
        Account oldAccount;

        //null check for the fields

        if (email == null || email == "") {
            Exception e = new Exception("Username cannot be null or empty.");
            throw e;
        }

        oldAccount = accountRepository.findAccountByEmail(email);
        if( oldAccount != null) {
            Exception e = new Exception("Account already exists under this email.");
            throw e;
        }

        if(password == null || password == "") {
            Exception e = new Exception("Password cannot be null or empty.");
            throw e;
        }
        account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setloginStatus(false);
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
    public Account getAccount(String email) throws Exception {
        if(email == null || email == "") {
            Exception e = new Exception("Couldn't find requested account with given info.");
            throw e;            
        }
        Account account = accountRepository.findAccountByEmail(email);
        Account new_acc;
        if(account != null) {
            new_acc = accountRepository.findAccountByEmail(email);
        }
        else {
            Exception e = new Exception("Couldn't find requested account with given info.");
            throw e;
        }
     return new_acc;
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


        if((newEmail == null && newPassword == null) || (newEmail == "" && newPassword == "")) {
            Exception e = new Exception("Can't modify an account without changed values");
            throw e;
        }

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
        if((email == null && password == null) || (email == "" && password == "")) {
            Exception e = new Exception("Can't delete an account for which parameters aren't given.");
            throw e;
        }

        Account account = new Account();
        accountRepository.deleteAccountByEmailAndPassword(email, password);
        account = accountRepository.findAccountByEmailAndPassword(email, password);

        if (account != null) {
            Exception e = new Exception("Account not deleted.");
            throw e;
        }
        return account;
     }

    /**
     * 
     * Get all accounts
     * 
     * @return all accounts in repo
     * 
     */

     @Transactional
     public List<Account> getAllAccounts() {
        return toList(accountRepository.findAll());
     }
    
     private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
