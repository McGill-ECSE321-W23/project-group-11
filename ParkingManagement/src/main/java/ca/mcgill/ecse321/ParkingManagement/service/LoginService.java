package ca.mcgill.ecse321.ParkingManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
import ca.mcgill.ecse321.ParkingManagement.dto.AccountDto;
import ca.mcgill.ecse321.ParkingManagement.model.Account;


@Service
public class LoginService {

    @Autowired
    AccountRepository accountRepository;


     /**
     * Verifies that the correct account is logged in with the correct email and password
     * @param email of the account
     * @param password of the account
     * @return loginStatus of the account (boolean)
     * @throws Exception if no account with that email exists or invalid password
     */
    @Transactional
    public boolean loginAccount(AccountDto accountTO) throws Exception {

        if(accountTO == null){
            throw new Exception("Account cannot be null.");
        }

        if(accountTO.getlogInStatus()){
            throw new Exception("Account is already logged in.");
        }

        if(accountRepository.existsByEmail(accountTO.getEmail())){

            Account account = accountRepository.findAccountByEmail(accountTO.getEmail());

            if (account.getPassword().equals(accountTO.getPassword())) {
                accountTO.setLogInStatus(true);
                return accountTO.getlogInStatus();

            } else {
                throw new Exception("Invalid password.");
            }

        }else{
            throw new Exception("No account with that email exists.");
        }
        
    }





     /**
     * Verifies that the correct account is logged out with the correct email 
     * @param email of the account
     * @return loginStatus of the account (boolean)
     * @throws Exception if no account with that email exists 
     */
    @Transactional
    public boolean logoutAccount(AccountDto accountTO) throws Exception {
        
        if(accountTO == null){
            throw new Exception("Account cannot be null.");
        }

        if(!accountTO.getlogInStatus()){
            throw new Exception("Account is already logged out.");
        }

        if(accountRepository.existsByEmail(accountTO.getEmail())){
            accountTO.setLogInStatus(false);
            return accountTO.getlogInStatus();

        }else{
            throw new Exception("No account with that email exists.");
        }
        
    }
     
}
