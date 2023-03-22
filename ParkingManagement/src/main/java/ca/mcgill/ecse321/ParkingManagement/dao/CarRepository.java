package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.Car;

public interface CarRepository extends CrudRepository<Car, String> {

	Car findCarBylicensePlate(String licensePlate);
	boolean existsBylicensePlate(String licensePlate);

	

}