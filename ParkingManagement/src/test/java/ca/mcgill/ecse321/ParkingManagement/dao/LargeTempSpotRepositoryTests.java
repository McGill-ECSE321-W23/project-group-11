package ca.mcgill.ecse321.ParkingManagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.ParkingManagement.model.*;

@SpringBootTest
public class LargeTempSpotRepositoryTests {

	@Autowired
	private LargeTempSpotRepository largeSpotRepo;
    @Autowired
    private CarRepository carRepository;

	@AfterEach
    @BeforeEach
	public void clearDatabase() {
		largeSpotRepo.deleteAll();
        carRepository.deleteAll();
	}

	@Test
	public void LargeTempSpotTest() {
		largeSpotRepo.deleteAll();

		// Make car
        String licencePlate = "NASARULES";
        Size size = Size.Large;
        Car car = new Car();   //make car
        car.setLicensePlate(licencePlate);
        car.setSize(size);
        //save the car
        carRepository.save(car);

        //make a regular temp spot
		LargeTempSpot largeSpot = new LargeTempSpot();
        largeSpot.setCar(car);
        largeSpotRepo.save(largeSpot);  //save it in repo
        int id = largeSpot.getId();   //make id

        //set everything to null
		largeSpot = null;

		// Get spot from repository
        largeSpot = largeSpotRepo.findLargeTempSpotById(id);

		//check if there is a spot
		assertNotNull(largeSpot);
        //check if the id is the same as teh one you saved
		assertEquals(id, largeSpot.getId());
        //check if theres a car
        assertNotNull(largeSpot.getCar());
        //check if licence plat is the same as the one you saved
		assertEquals(licencePlate, largeSpot.getCar().getLicensePlate());
	}
}