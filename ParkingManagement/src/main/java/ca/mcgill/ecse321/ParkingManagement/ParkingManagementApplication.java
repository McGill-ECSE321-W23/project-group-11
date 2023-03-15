package ca.mcgill.ecse321.ParkingManagement;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ParkingManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingManagementApplication.class, args);
	}

}
