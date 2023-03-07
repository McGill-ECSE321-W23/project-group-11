package ca.mcgill.ecse321.ParkingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarRepositoryTests {

	@Autowired
	private CarRepository carRepository;

	@AfterEach
	public void clearDatabase() {
		carRepository.deleteAll();
	}


	@Test
	void carTest() {
		carRepository.deleteAll();

		// make car
		// save car to repository

		// add objects car will be associated to


		// set variables to null

		// get car from repository

		// check that everthing exists as it should
		assertNotNull(getClass());
		assertEquals(getClass(), getClass()); // excpected, actual
	}
}