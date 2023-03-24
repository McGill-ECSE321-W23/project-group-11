package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpot;

public interface LargeTempSpotRepository extends CrudRepository<LargeTempSpot, Integer> {
	LargeTempSpot findLargeTempSpotById(int id);
	boolean existsById(int id);
	boolean existsByPlaceNumber(int placeNumber);
	LargeTempSpot findByPlaceNumber(int placeNumber);
}