package ca.mcgill.ecse321.ParkingManagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Size;
import ca.mcgill.ecse321.ParkingManagement.model.RegularTempSpot;

@SpringBootTest
public class RegularTempSpotRepositoryTests {

	@Autowired
	private RegularTempSpotRepository regSpotRepo;

    @Autowired
	private LargeTempSpotRepository largeSpotRepo;

    @Autowired
    private CarRepository carRepository;

	@AfterEach
    @BeforeEach
	public void clearDatabase() {
		regSpotRepo.deleteAll();
        carRepository.deleteAll();
        largeSpotRepo.deleteAll();
	}

	@Test
	public void regularTempSpotTest() {
		regSpotRepo.deleteAll();

		// Make car
        String licencePlate = "NASARULES";
        Size size = Size.Regular;
        Car car = new Car();   //make car
        car.setLicensePlate(licencePlate);
        car.setSize(size);
        //save the car
        carRepository.save(car);

        //make a regular temp spot
		RegularTempSpot regSpot = new RegularTempSpot();
        regSpot.setCar(car);
        regSpotRepo.save(regSpot);  //save it in repo
        int id = regSpot.getId();   //make id


        //set everything to null
		regSpot = null;

		// Get spot from repository
        regSpot = regSpotRepo.findRegularTempSpotById(id);

		//check if there is a spot
		assertNotNull(regSpot);
        //check if the id is the same as teh one you saved
		assertEquals(id, regSpot.getId());
        //check if theres a car
        assertNotNull(regSpot.getCar());
        //check if licence plat is the same as the one you saved
		assertEquals(licencePlate, regSpot.getCar().getLicensePlate());
	}
}