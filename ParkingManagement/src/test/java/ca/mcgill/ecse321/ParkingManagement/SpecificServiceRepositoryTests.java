// package ca.mcgill.ecse321.ParkingManagement;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import java.sql.Date;
// import java.sql.Time;
// import ca.mcgill.ecse321.ParkingManagement.dao.*;
// import ca.mcgill.ecse321.ParkingManagement.model.*;

// @SpringBootTest
// public class SpecificServiceRepositoryTests{

// 	@Autowired
// 	private SpecificServiceRepository specificServiceRepository;
//     @Autowired
//     private ManagerRepository managerRepository;
//     @Autowired 
//     private CarRepository carRepository;
//     @Autowired
//     private ServiceTypeRepository serviceTypeRepository;

// 	@AfterEach
// 	public void clearDatabase() {
// 		serviceTypeRepository.deleteAll();
//         managerRepository.deleteAll();
//         carRepository.deleteAll();
//         specificServiceRepository.deleteAll();
// 	}

// 	@Test
// 	public void specificServiceTest() {
// 		serviceTypeRepository.deleteAll();
//         managerRepository.deleteAll();
//         carRepository.deleteAll();
//         specificServiceRepository.deleteAll();

// 		// Make car
// 		String licensePlate = "NASARULES";
// 		Car car = new Car();
// 		car.setLicensePlate(licensePlate);
// 		// Save car to repository and get it's ID (this will test the getter as well)
// 		car = carRepository.save(car);

//         // // Make manager
//         Manager manager = new Manager();
//         int managerId = 1;
//         manager.setId(managerId);
//         //save the manager
//         managerRepository.save(manager);

//         //make a servicetype
// 	    String name = "Tire Change";   //make service type name
// 		// int duration = 30; //set duration
//         // int cost = 20; //set cost
//         ServiceType serviceType = new ServiceType();
//         // serviceType.setCost(cost);
//         // serviceType.setDuration(duration);
//         serviceType.setName(name);
//         serviceType.setManager(manager);
//         serviceTypeRepository.save(serviceType);  //save it in repo

//         //make a specificservice
// 	    int id = 123;
//         Date date = Date.valueOf("2022-09-06");
//         Time startTime = Time.valueOf("16:00:00");
//         String employee = "Bob";
//         SpecificService specificService = new SpecificService();

//         //set all attributes
//         specificService.setCar(car);
//         specificService.setDate(date);
//         specificService.setStartTime(startTime);
//         specificService.setEmployee(employee);
//         specificService.setServiceType(serviceType);
//         specificService.setId(id);
//         //save in database
//         specificServiceRepository.save(specificService);

//         //set everything to null
// 		specificService = null;

// 		// Get spot from repository
//         specificService = specificServiceRepository.findSpecificServiceById(id);

// 		//check if there is a specific service
// 		assertNotNull(specificService);
//         //check if the attributes are the same as the ones you saved
// 		assertEquals(id, specificService.getId());
//         assertEquals(date, specificService.getDate());
//         assertEquals(startTime, specificService.getStartTime());
//         assertEquals(employee, specificService.getEmployee());
//         //check if theres a service type
//         assertNotNull(specificService.getServiceType());
//         //check if service is the same as the one saved
// 		assertEquals(name, specificService.getServiceType().getName());
//         //check if theres a car
//         assertNotNull(specificService.getCar());
//         //check if its the same car you saved
//         assertEquals(licensePlate, specificService.getCar().getLicensePlate());
// 	}
// }