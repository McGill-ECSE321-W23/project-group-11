package ca.mcgill.ecse321.ParkingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.ParkingManagement.dao.ServiceTypeRepository;



@SpringBootTest
class ServiceTypeTests {

	@Autowired
	private ServiceType ServiceType;

	@AfterEach
	public void clearDatabase() {
		ServiceType.deleteAll();
	}


	@Test
	void ServiceTypeTest() {
		ServiceType.deleteAll();

		// make ServiceType
		String name = "oil change";
		String cost = "10$";
		String duration = "2 hours";

		ServiceType oilchange = new ServiceType();
		oilchange.setName(name);
		oilchange.setcost(cost);
		oilchange.setduration(duration);


		// save ServiceType to repository
		oilchnage = ServiceTypeRepository.save(oilchange);
		int id = oilchange.getId();

		// add objects ServiceType will be associated to
		oilchange = ServiceTypeRepository.findServiceTypeById(id);

		// check that everthing exists as it should
		assertNotNull(oilchange);
		assertEquals(name, oilchange.getname()); // excpected, actual
	}
}