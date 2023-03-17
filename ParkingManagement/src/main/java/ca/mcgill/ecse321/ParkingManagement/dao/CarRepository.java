package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, String> {

	Car findCarBylicensePlate(String licensePlate);
	boolean existsBylicensePlate(String licensePlate);

	

}