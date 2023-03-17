package ca.mcgill.ecse321.ParkingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.Time;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@SpringBootTest
public class SystemInfoRepositoryTests {
    
    @Autowired
    private SystemInfoRepository systemInfoRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @BeforeEach
    @AfterEach
	public void clearDatabase() {
		systemInfoRepository.deleteAll();
        managerRepository.deleteAll();

	}

    @Test
    public void testPersistAndLoadSystemInfo() {

        // Create manager
        int managerId = 1;
        Manager manager = new Manager();
        manager.setId(managerId);
        // Save manager
        managerRepository.save(manager);
    
        // Create SystemInfo object
        SystemInfo systemInfo = new SystemInfo();
        int id = 123;
        Time openTime = Time.valueOf("08:00:00");
        Time closeTime = Time.valueOf("20:00:00");
        int largeTempSpotPrice = 5;
        int smallTempSpotPrice = 3;
        int reservedSpotPrice = 10;
        systemInfo.setCloseTime(closeTime);
        systemInfo.setOpenTime(openTime);
        systemInfo.setId(id);
        systemInfo.setLargeTempSpotPrice(largeTempSpotPrice);
        systemInfo.setSmallTempSpotPrice(smallTempSpotPrice);
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
        assertEquals(smallTempSpotPrice, systemInfo.getSmallTempSpotPrice(), "Small temporary spot price should match");
        assertNotNull(systemInfo.getManager(), "Manager object should not be null");
        assertNotNull(systemInfo.getManager().getId(), "Manager ID should not be null");
        assertEquals(managerId, systemInfo.getManager().getId(), "Manager ID should match");
    }

}