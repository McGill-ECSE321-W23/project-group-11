// package ca.mcgill.ecse321.ParkingManagement;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import ca.mcgill.ecse321.ParkingManagement.dao.ServiceTypeRepository;
// import ca.mcgill.ecse321.ParkingManagement.model.ServiceType;



// @SpringBootTest
// class ServiceTypeTests {

// 	@Autowired
// 	ServiceTypeRepository serviceTypeRepository;
	

// 	@AfterEach
// 	public void clearDatabase() {
// 		serviceTypeRepository.deleteAll();
// 	}


// 	@Test
// 	void ServiceTypeTest() {

// 		serviceTypeRepository.deleteAll();

// 		// make ServiceType
// 		String name = "oil change";
// 		String cost = "10$";
// 		String duration = "2 hours";

// 		ServiceType oilchange = new ServiceType();
// 		oilchange.setName(name);
// 		oilchange.setCost(cost);
// 		oilchange.setDuration(duration);


// 		// save ServiceType to repository
// 		oilchange = serviceTypeRepository.save(oilchange);
// 		String id = oilchange.getName();

// 		// add objects ServiceType will be associated to
// 		oilchange = serviceTypeRepository.findServiceTypeByName(id);

// 		// check that everthing exists as it should
// 		assertNotNull(oilchange);
// 		assertEquals(name, oilchange.getName()); // excpected, actual
// 	}
// }