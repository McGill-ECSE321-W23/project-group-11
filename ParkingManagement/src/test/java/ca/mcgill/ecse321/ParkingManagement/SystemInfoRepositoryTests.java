package ca.mcgill.ecse321.ParkingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
	public void clearDatabase() {
		systemInfoRepository.deleteAll();
        managerRepository.deleteAll();

	}

    @Test
	public void testPersistAndLoadSystemInfo() {
        // Make manager
        int managerId = 1;
        Manager manager = new Manager();
        manager.setId(managerId);
        //save the manager
        managerRepository.save(manager);

		// Create SystemInfo Object
        SystemInfo systemInfo = new SystemInfo();
        int id = 11;
        Time openTime = Time.valueOf("08:00:00");
        Time closeTime= Time.valueOf("20:00:00");
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

        systemInfo = null;  //clear object

        // Read object from database
		systemInfo = systemInfoRepository.findSystemInfoById(id);

		// Assert that object has correct attributes
		assertNotNull(systemInfo);
		assertEquals(closeTime, systemInfo.getCloseTime());
		assertEquals(openTime, systemInfo.getOpenTime());
		assertEquals(largeTempSpotPrice, systemInfo.getLargeTempSpotPrice());
        assertEquals(reservedSpotPrice, systemInfo.getReservedSpotPrice());
        assertEquals(smallTempSpotPrice, systemInfo.getSmallTempSpotPrice());
        assertNotNull(systemInfo.getManager());
        assertEquals(managerId, systemInfo.getManager().getId());
    }

}