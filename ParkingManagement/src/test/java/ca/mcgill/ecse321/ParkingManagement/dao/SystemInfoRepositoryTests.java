package ca.mcgill.ecse321.ParkingManagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Time;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@SpringBootTest
public class SystemInfoRepositoryTests {
    
    @Autowired
    private SystemInfoRepository systemInfoRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    @AfterEach
	public void clearDatabase() {
		systemInfoRepository.deleteAll();
        managerRepository.deleteAll();        
        accountRepository.deleteAll();
	}

    @Test
    public void testPersistAndLoadSystemInfo() {

        //create account
        String email = "johndoe1955@gmail.com";
        String password = "password";
        boolean loginStatus = false;
        Account account = new Account();
        account.setEmail(email);
        account.setPassword(password);
        account.setloginStatus(loginStatus);

        //save account
        accountRepository.save(account);

        // Create manager
        Manager manager = new Manager();
        manager.setAccount(account);
        
        // Save manager
        manager = managerRepository.save(manager);
    
        // Create SystemInfo object
        SystemInfo systemInfo = new SystemInfo();
        int id = 123;
        Time openTime = Time.valueOf("08:00:00");
        Time closeTime = Time.valueOf("20:00:00");
        int largeTempSpotPrice = 5;
        int regTempSpotPrice = 3;
        int reservedSpotPrice = 10;
        systemInfo.setCloseTime(closeTime);
        systemInfo.setOpenTime(openTime);
        systemInfo.setId(id);
        systemInfo.setLargeTempSpotPrice(largeTempSpotPrice);
        systemInfo.setRegTempSpotPrice(regTempSpotPrice);
        systemInfo.setReservedSpotPrice(reservedSpotPrice);
        systemInfo.setManager(manager);
        // Save object
        systemInfo = systemInfoRepository.save(systemInfo);
    
        // Read object from database
        systemInfo = systemInfoRepository.findSystemInfoById(id);
    
        // Assert that object has correct attributes
        assertNotNull(systemInfo, "SystemInfo object should not be null");
        assertEquals(closeTime, systemInfo.getCloseTime(),"Close time should match");
        assertEquals(openTime, systemInfo.getOpenTime(),"Open time should match");
        assertEquals(largeTempSpotPrice, systemInfo.getLargeTempSpotPrice(), "Large temporary spot price should match");
        assertEquals(reservedSpotPrice, systemInfo.getReservedSpotPrice(), "Reserved spot price should match");
        assertEquals(regTempSpotPrice, systemInfo.getRegTempSpotPrice(), "Regular temporary spot price should match");
        assertNotNull(systemInfo.getManager(), "Manager object should not be null");
        assertNotNull(systemInfo.getManager().getId(), "Manager ID should not be null");
        assertEquals(manager.getId(), systemInfo.getManager().getId(), "Manager ID should match");
    }
}