package ca.mcgill.ecse321.ParkingManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
    private static final String NONEXISTING_KEY = "NotAPerson";

@BeforeEach
public void setMockOutput() {
    lenient().when(accountDao.findAccountByEmailAndPassword(anyString(), anyString())).thenAnswer((InvocationOnMock 
    invocation) -> {
            if(invocation.getArgument(0).equals(PERSON_KEY)) {
                Account account = new Account();
                account.setEmail(PERSON_KEY);
                account.setPassword(PERSON_PW);
                return account;
            } else {
                return null;
            }



    });

    Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
        return invocation.getArgument(0);
    };
    lenient().when(accountDao.save(any(Account.class))).thenAnswer(returnParameterAsAnswer);

}

@Test
public void testCreateAccount() {
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
public void testGetAccount() {
    Account account = null;
    Account a = null;
    String email = "anakin@padme.com";
    String pw = "I_H8_U";
    try{
        account = service.createAccount(email, pw);
        accountDao.save(account);
        a=service.getAccount(email, pw);
        assertNotNull(a);
        assertEquals(email, a.getEmail());
        assertEquals(pw,a.getPassword());
    } catch (Exception e) {
        fail(e);
    }
 
}

@Test
public void testModificationAccount() {
    String ben = "ben_ken@jedi.gal";
    String ben_pw = "I_Have_TheHighGround";
    try{    
        Account a = service.getAccount(PERSON_KEY, PERSON_PW);
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
    try{
        Account a = service.deleteAccount("ben_ken@jedi.gal", "I_Have_TheHighGround");
        assertEquals(null, a);
    } catch (Exception e) {
        fail(e);
    }
}

}
