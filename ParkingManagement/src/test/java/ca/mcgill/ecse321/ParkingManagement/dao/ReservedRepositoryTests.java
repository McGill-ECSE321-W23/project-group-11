package ca.mcgill.ecse321.ParkingManagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ca.mcgill.ecse321.ParkingManagement.dao.ReservedSpotRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.CarRepository;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Size;
import ca.mcgill.ecse321.ParkingManagement.model.ReservedSpot;

@SpringBootTest
public class ReservedRepositoryTests {

	@Autowired
	private ReservedSpotRepository reservedSpotRepo;
    @Autowired
    private CarRepository carRepository;

	@AfterEach
    @BeforeEach
	public void clearDatabase() {
		reservedSpotRepo.deleteAll();
        carRepository.deleteAll();
	}

	@Test
	public void reservedSpotTest() {
		reservedSpotRepo.deleteAll();
        carRepository.deleteAll();
		// Make car
        String licencePlate = "NASARULES";
        Size size = Size.Regular;
        Car car = new Car();   //make car
        car.setLicensePlate(licencePlate);
        car.setSize(size);
        //save the car
        carRepository.save(car);

        //make a regular temp spot
		ReservedSpot reservedSpot = new ReservedSpot();
        reservedSpot.setCar(car);
        reservedSpotRepo.save(reservedSpot);  //save it in repo
        int id = reservedSpot.getId();

        //set everything to null
		reservedSpot = null;

		// Get spot from repository
        ReservedSpot reservedSpotFromDB = reservedSpotRepo.findReservedSpotById(id);

		//check if there is a spot
		assertNotNull(reservedSpotFromDB);
        //check if the id is the same as teh one you saved
		assertEquals(id, reservedSpotFromDB.getId());
        //check if theres a car
        assertNotNull(reservedSpotFromDB.getCar());
        //check if licence plat is the same as the one you saved
		assertEquals(licencePlate, reservedSpotFromDB.getCar().getLicensePlate());
	}
}