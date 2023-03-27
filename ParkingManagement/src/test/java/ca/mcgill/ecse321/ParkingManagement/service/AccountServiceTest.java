package ca.mcgill.ecse321.ParkingManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountDao;

    @InjectMocks
    private AccountService service;

    private static final String PERSON_KEY = "anakin@padme.com";
    private static final String PERSON_PW = "I_H8_U";

	@BeforeEach
	public void clearDatabase() {
		accountDao.deleteAll();
	}

@Test
public void testCreateAccount() {
    accountDao.deleteAll();
    assertEquals(0,service.getAllAccounts().size());

    String email = "anakin@padme.com";
    String pw = "I_H8_U";

    Account account = null;

    try {
        account = service.createAccount(email, pw);
    } catch (Exception e) {
        fail(e);
    }
    assertNotNull(account);
    assertEquals(email, account.getEmail());
    assertEquals(pw, account.getPassword());
}

@Test
public void createSameEmailAccount() {
    accountDao.deleteAll();
    Account account = null;
    Account dupAccount = null;
    String error = null;
    try {
        account = service.createAccount(PERSON_KEY, PERSON_PW);
        accountDao.save(account);
        dupAccount = service.createAccount(PERSON_KEY, PERSON_PW);
    }
    catch (Exception e) {
        error = e.getMessage();
    }
    assertNotNull(account);
    assertEquals("Account already exists under this email.", error);
}

@Test
public void createEmptyEmailAccount() {
    accountDao.deleteAll();
    Account account = null;
    String error = null;
    try {
        account = service.createAccount("", PERSON_PW);
    }
    catch (Exception e) {
        error = e.getMessage();
    }
    assertNull(account);
    assertEquals("Username cannot be null or empty.", error);
}

@Test
public void createNullEmailAccount() {
    accountDao.deleteAll();
    Account account = null;
    String error = null;
    try {
        account = service.createAccount(null, PERSON_PW);
    }
    catch (Exception e) {
        error = e.getMessage();
    }
    assertNull(account);
    assertEquals("Username cannot be null or empty.", error);
}

@Test 
public void createNullPasswordAccount() {
    accountDao.deleteAll();
    Account account = null;
    String error = null;
    try {
        account = service.createAccount(PERSON_KEY, null);
    }
    catch (Exception e) {
        error = e.getMessage();
    }
    assertNull(account);  
    assertEquals("Password cannot be null or empty.", error); 
}

@Test 
public void createEmptyPasswordAccount() {
    accountDao.deleteAll();
    Account account = null;
    String error = null;
    try {
        account = service.createAccount(PERSON_KEY, "");
    }
    catch (Exception e) {
        error = e.getMessage();
    }
    assertNull(account);
    assertEquals("Password cannot be null or empty.", error);   
}

@Test
public void testGetAccount() {
    Account account = null;
    Account a = null;
    String email = "anakin@padme.com";
    String pw = "I_H8_U";
    try{
        account = service.createAccount(email, pw);
        accountDao.save(account);
        a=service.getAccount(email);

    } catch (Exception e) {
        fail(e);
    }
    assertNotNull(account);
    assertNotNull(a);
    assertEquals(email, a.getEmail());
    assertEquals(pw,a.getPassword());
 
}

@Test
public void testModificationAccount() {
    String ben = "ben_ken@jedi.gal";
    String ben_pw = "I_Have_TheHighGround";


    try{    
        Account account = service.createAccount(PERSON_KEY, PERSON_PW);
        Account a = service.getAccount(PERSON_KEY);
        a = service.editAccount(a, ben, null);
        assertEquals(ben,a.getEmail());
        a=service.editAccount(a, null, ben_pw);
        assertEquals(ben,a.getPassword());
        accountDao.save(a);
    } catch (Exception e) {
        fail(e);
    }


}

@Test
public void testDeletionAccount() {
    Account dummy;
    try{
        dummy = service.createAccount("ben_ken@jedi.gal", "I_Have_TheHighGround");
        assertEquals(1,service.getAllAccounts().size());
        Account a = service.deleteAccount("ben_ken@jedi.gal", "I_Have_TheHighGround");

    } catch (Exception e) {
        fail(e);
    }
    Account a = accountDao.findAccountByEmailAndPassword("ben_ken@jedi.gal", "I_Have_TheHighGround");
    assertEquals(null, a);
    assertEquals(0,service.getAllAccounts().size());
}

}
