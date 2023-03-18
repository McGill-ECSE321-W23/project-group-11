package ca.mcgill.ecse321.ParkingManagement.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.AfterEach;
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
	    int id = 111;   //make id
		ReservedSpot reservedSpot = new ReservedSpot();
        reservedSpot.setId(id);
        reservedSpot.setCar(car);
        reservedSpotRepo.save(reservedSpot);  //save it in repo

        //set everything to null
		reservedSpot = null;

		// Get spot from repository
        reservedSpot = reservedSpotRepo.findReservedSpotById(id);

		//check if there is a spot
		assertNotNull(reservedSpot);
        //check if the id is the same as teh one you saved
		assertEquals(id, reservedSpot.getId());
        //check if theres a car
        assertNotNull(reservedSpot.getCar());
        //check if licence plat is the same as the one you saved
		assertEquals(licencePlate, reservedSpot.getCar().getLicensePlate());
	}
}