package ca.mcgill.ecse321.ParkingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ca.mcgill.ecse321.ParkingManagement.dao.CarRepository;
import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Size;

@SpringBootTest
public class CarRepositoryTests {

	@Autowired
	private CarRepository carRepository;

	@AfterEach
	public void clearDatabase() {
		carRepository.deleteAll();
	}

	@Test
	public void carTest() {
		carRepository.deleteAll();

		// Make car
		String licensePlate = "NASARULES";
		Size size = Size.Regular;
		Car car = new Car();
		car.setLicensePlate(licensePlate);
		car.setSize(size);

		// Save car to repository and get it's ID (this will test the getter as well)
		car = carRepository.save(car);
		String id = car.getLicensePlate();

		// Add account that will be linked with car
		Account account = new Account();
		Set<Car> cars = new HashSet<Car>();
		cars.add(car);
		account.setCar(cars);

		// Set variables to null
		car = null;
		account = null;

		// Get car from repository
		car = carRepository.findCarBylicensePlate(id);

		// Check that everthing exists as it should
		// Car checks
		assertNotNull(car);
		assertEquals("NASARULES", car.getLicensePlate()); // excpected, actual
		assertEquals(Size.Regular, car.getSize());
		// Associated account check
		assertEquals("bob@email.com", car.getAccount().getEmail()); 

	}
}