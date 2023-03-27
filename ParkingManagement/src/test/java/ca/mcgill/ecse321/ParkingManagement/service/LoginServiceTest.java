package ca.mcgill.ecse321.ParkingManagement.service;

// All imports recommended by tutorial notes 2.8.1-2.8.2
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.dto.AccountDto;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
	private LoginService service;


    @BeforeEach
	public void clearDatabase() {//not needed?
		accountRepository.deleteAll();
	}


    //null test cases
    //test cases of not existing in the database

    @Test
    public void testValidLogin() {//account that hasn't already logged in should be able to login
        
        AccountDto accountDto = new AccountDto("brianMorava123@gmail.com", "password", false);
        Account account = new Account();
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());
        when(accountRepository.existsByEmail(any(String.class))).thenReturn(true);
        when(accountRepository.findAccountByEmail(any(String.class))).thenReturn(account);
        try {
            service.loginAccount(accountDto);
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }


    @Test
    public void testInvalidLogin() {//account that is already logged in shouldn't be able to login
        assertThrows(Exception.class,
                () -> {
                    AccountDto accountDto = new AccountDto("brianMorava123@gmail.com", "password", true);
                    service.loginAccount(accountDto);
                }
        );

    }


    @Test
    public void testValidLogout() {//account that is already logged in should be able to logout
       
        AccountDto accountDto = new AccountDto("brian@gmail.com", "password1", true);
        when(accountRepository.existsByEmail(any(String.class))).thenReturn(true);

        try {
            service.logoutAccount(accountDto);
        } catch (Exception e) {
            fail("Should not throw any exception");
        }
    }



    @Test
    public void testInvalidLogout() {//account that is already logged out shouldn't be able to logout
        assertThrows(Exception.class,
                () -> {
                    AccountDto accountDto = new AccountDto("brianMorava123@gmail.com", "password", false);
                    service.logoutAccount(accountDto);
                }
        );

    }
    
}
