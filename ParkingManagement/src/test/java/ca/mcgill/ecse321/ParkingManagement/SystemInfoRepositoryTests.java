// package ca.mcgill.ecse321.ParkingManagement;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import ca.mcgill.ecse321.ParkingManagement.dao.SystemInfoRepository;
// import ca.mcgill.ecse321.ParkingManagement.model.*;

// @SpringBootTest
// public class SystemInfoRepositoryTests {
    
//     @Autowired
//     private SystemInfoRepository systemInfoRepository;

//     @AfterEach
// 	public void clearDatabase() {
// 		systemInfoRepository.deleteAll();
// 	}

//     @Test
// 	public void testPersistAndLoadSystemInfo() {
// 		// Create SystemInfo Object
//         SystemInfo systemInfo = new SystemInfo();
// 		String openTime = "6:00";
// 		String closeTime = "23:00";
// 		int id = 1;
		
//         systemInfo.setCloseTime(closeTime);
//         systemInfo.setOpenTime(openTime);
// 		systemInfo.setId(id);


// 		// Save object
// 		systemInfo = systemInfoRepository.save(systemInfo);

// 		// Read object from database
// 		systemInfo = systemInfoRepository.findSystemInfoById(id);

// 		// Assert that object has correct attributes
// 		assertNotNull(systemInfo);
// 		assertEquals(closeTime, systemInfo.getCloseTime());
// 		assertEquals(openTime, systemInfo.getOpenTime());
// 		assertEquals(id, systemInfo.getId());
//     }

// }