package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpot;

@Repository
public interface LargeTempSpotRepository extends CrudRepository<LargeTempSpot, Integer> {
	LargeTempSpot findLargeTempSpotById(int id);
	boolean existsById(int id);
}