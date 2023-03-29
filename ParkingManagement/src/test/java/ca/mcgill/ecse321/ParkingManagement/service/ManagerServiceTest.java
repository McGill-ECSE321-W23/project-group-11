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
public class ManagerServiceTest {

    @Mock
    AccountRepository accountDao;

    @Mock
    ManagerRepository managerDao;

    @InjectMocks
    private AccountService acService;

    @InjectMocks
    private ManagerService manService;

    private static final String PERSON_KEY = "anakin@padme.com";
    private static final String PERSON_PW = "I_H8_U";
    

    @Test
    public void testGetManagerByEmail() throws Exception {
        Account account = acService.createAccount(PERSON_PW, PERSON_KEY);
        Manager manager = new Manager();
        Manager findManager = null;
        String error = null;
        manager.setAccount(account);
        when(accountDao.findAccountByEmail(PERSON_KEY)).thenReturn(account);
        when(managerDao.findManagerByAccount(account)).thenReturn(manager);

        try{
            findManager = manService.getManagerByEmail(PERSON_KEY);
        }
        catch(Exception e) {
            error = e.getMessage();
        }
        assertNotNull(findManager);
        assertNull(error);
        assertEquals(account,findManager.getAccount());

    }

    @Test
    public void testGetManagerByNullEmail() throws Exception {
        Account account = acService.createAccount(PERSON_PW, PERSON_KEY);
        Manager manager = new Manager();
        Manager findManager = null;
        String error = null;
        manager.setAccount(account);

        try{
            findManager = manService.getManagerByEmail(null);
        }
        catch(Exception e) {
            error = e.getMessage();
        }
        assertNull(findManager);
        assertNotNull(error);
        assertEquals("Can't find manager with empty email.",error);

    }

    @Test
    public void testGetManagerByEmptyEmail() throws Exception {
        Account account = acService.createAccount(PERSON_PW, PERSON_KEY);
        Manager manager = new Manager();
        Manager findManager = null;
        String error = null;
        manager.setAccount(account);

        try{
            findManager = manService.getManagerByEmail("");
        }
        catch(Exception e) {
            error = e.getMessage();
        }
        assertNull(findManager);
        assertNotNull(error);
        assertEquals("Can't find manager with empty email.",error);

    }

    @Test
    public void testGetManagerByWrongEmail() throws Exception {
        Account account = acService.createAccount(PERSON_PW, PERSON_KEY);
        Manager manager = new Manager();
        Manager findManager = null;
        String error = null;
        manager.setAccount(account);

        try{
            findManager = manService.getManagerByEmail("obi_wan@jedi.com");
        }
        catch(Exception e) {
            error = e.getMessage();
        }
        assertNull(findManager);
        assertNotNull(error);
        assertEquals("No account with this email.",error);

    }

    @Test
    public void testGetManagerByWrongAccount() throws Exception {
        Account account = acService.createAccount(PERSON_PW, PERSON_KEY);
        Account fake_account = acService.createAccount("Obi_wan@gmail.com", "you_wereABrother");
        Manager manager = new Manager();
        manager.setAccount(account);
        Manager findManager = null;
        String error = null;
        when(accountDao.findAccountByEmail("Obi_wan@gmail.com")).thenReturn(fake_account);

        try{
            findManager = manService.getManagerByEmail("Obi_wan@gmail.com");
        }
        catch(Exception e) {
            error = e.getMessage();
        }
        assertNull(findManager);
        assertNotNull(error);
        assertEquals("No manager with this account.",error);

    }


}
