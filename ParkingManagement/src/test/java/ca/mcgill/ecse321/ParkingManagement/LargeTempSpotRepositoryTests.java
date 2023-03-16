package ca.mcgill.ecse321.ParkingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@SpringBootTest
public class LargeTempSpotRepositoryTests {

	@Autowired
	private LargeTempSpotRepository largeSpotRepo;
    @Autowired
    private CarRepository carRepository;

	@AfterEach
	public void clearDatabase() {
		largeSpotRepo.deleteAll();
        carRepository.deleteAll();
	}

	@Test
	public void regularTempSpotTest() {
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
	    int id = 111;   //make id
		LargeTempSpot largeSpot = new LargeTempSpot();
        largeSpot.setId(id);
        largeSpot.setCar(car);
        largeSpotRepo.save(largeSpot);  //save it in repo

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