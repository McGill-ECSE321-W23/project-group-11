package ca.mcgill.ecse321.ParkingManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountDao;

    @InjectMocks
    private AccountService service;

    private static final String PERSON_KEY = "anakin@padme.com";
    private static final String PERSON_PW = "I_H8_U";


@Test
public void testCreateAccount() {
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
    Account account = new Account();
    account.setEmail(PERSON_KEY);
    account.setPassword(PERSON_PW);
    when(accountDao.findAccountByEmail(PERSON_KEY)).thenReturn(account);
    Account dupAccount = null;
    String error = null;
    try {
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
    Account account = new Account();
    Account a = null;
    String email = "anakin@padme.com";
    String pw = "I_H8_U";
    account.setEmail(email);
    account.setPassword(pw);
    when(accountDao.findAccountByEmail(any(String.class))).thenReturn(account);
    try{
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
public void testGetNullEmail() {
    Account account = null;
    String email = null;
    Account a = null;
    String error = null;
    try{
        a=service.getAccount(email);

    } catch (Exception e) {
        error = e.getMessage();
    }
    assertNull(a);
    assertEquals("Couldn't find requested account with given info.", error);
   
}

@Test
public void testGetEmptyEmail() {
    Account account = null;
    String email = "";
    Account a = null;
    String error = null;
    try{
        a=service.getAccount(email);

    } catch (Exception e) {
        error = e.getMessage();
    }
    assertNull(a);
    assertEquals("Couldn't find requested account with given info.", error);
   
}

@Test
public void testModificationAccount() {
    String ben = "ben_ken@jedi.gal";
    String ben_pw = "I_Have_TheHighGround";
    Account account = new Account();
    account.setEmail(PERSON_KEY);
    account.setPassword(PERSON_PW);
    when(accountDao.findAccountByEmail(any(String.class))).thenReturn(account);


    try{    
        Account a = service.getAccount(PERSON_KEY);
        a = service.editAccount(a, ben, null);
        assertEquals(ben,a.getEmail());
        a=service.editAccount(a, null, ben_pw);
        assertEquals(ben_pw,a.getPassword());
    } catch (Exception e) {
        fail(e);
    }


}

@Test
public void testIllegalNullModificationAccount() {
    String ben = null;
    String ben_pw = null;
    Account account = new Account();
    account.setEmail(PERSON_KEY);
    account.setPassword(PERSON_PW);
    String error = null;
    when(accountDao.findAccountByEmail(any(String.class))).thenReturn(account);
    try{    
        Account a = service.getAccount(PERSON_KEY);
        a = service.editAccount(a, ben, ben_pw);
    } catch (Exception e) {
        error = e.getMessage();
    }
    assertEquals("Can't modify an account without changed values", error);
    assertEquals(PERSON_KEY, account.getEmail());
    assertEquals(PERSON_PW, account.getPassword());

}

@Test
public void testIllegalEmptyModificationAccount() {
    String ben = "";
    String ben_pw = "";
    Account account = new Account();
    account.setEmail(PERSON_KEY);
    account.setPassword(PERSON_PW);
    String error = null;
    when(accountDao.findAccountByEmail(any(String.class))).thenReturn(account);
    try{    
        Account a = service.getAccount(PERSON_KEY);
        a = service.editAccount(a, ben, ben_pw);
    } catch (Exception e) {
        error = e.getMessage();
    }
    assertEquals("Can't modify an account without changed values", error);
    assertEquals(PERSON_KEY, account.getEmail());
    assertEquals(PERSON_PW, account.getPassword());

}

@Test
public void testDeletionValidAccount() {
    Account dummy = new Account();
    Account account = new Account();
    account.setEmail(PERSON_KEY);
    account.setPassword(PERSON_PW);
    when(accountDao.findAccountByEmailAndPassword(any(String.class),any(String.class))).thenReturn(null);

    try{
    dummy = service.deleteAccount(PERSON_KEY,PERSON_PW);

    } catch (Exception e) {
        fail(e);
    }
    assertEquals(null, dummy);
    assertEquals(0,service.getAllAccounts().size());
}

@Test
public void testDeletionInvalidNullParam() {
    Account dummy = null;
    Account account = new Account();
    account.setEmail(PERSON_KEY);
    account.setPassword(PERSON_PW);
    String wrongEmail = null;
    String wrongPW = null;
    String error = null;

    try{
    dummy = service.deleteAccount(wrongEmail,wrongPW);

    } catch (Exception e) {
        error = e.getMessage();
    }
    assertEquals("Can't delete an account for which parameters aren't given.", error);
}

@Test
public void testDeletionInvalidEmptyParam() {
    Account dummy = null;
    Account account = new Account();
    account.setEmail(PERSON_KEY);
    account.setPassword(PERSON_PW);
    String wrongEmail = "";
    String wrongPW = "";
    String error = null;


    try{
    dummy = service.deleteAccount(wrongEmail,wrongPW);

    } catch (Exception e) {
        error = e.getMessage();
    }
    assertEquals("Can't delete an account for which parameters aren't given.", error);
}

@Test
public void testDeletionInvalidWrongParam() {
    Account dummy = null;
    Account account = new Account();
    account.setEmail(PERSON_KEY);
    account.setPassword(PERSON_PW);
    String wrongEmail = "adada";
    String wrongPW = "dazfezfze";
    String error = null;
    when(accountDao.findAccountByEmailAndPassword(any(String.class),any(String.class))).thenReturn(account);

    try{
    dummy = service.deleteAccount(wrongEmail,wrongPW);

    } catch (Exception e) {
        error = e.getMessage();
    }
    assertEquals("Account not deleted.", error);
}


}
