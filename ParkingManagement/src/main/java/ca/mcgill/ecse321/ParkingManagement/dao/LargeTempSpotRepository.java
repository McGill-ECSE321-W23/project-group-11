package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpots;

@Repository
public interface LargeTempSpotsRepository extends CrudRepository<LargeTempSpots, Integer> {

	LargeTempSpots findLargeTempSpotsByUsername(String username);
	LargeTempSpots findLargeTempSpotsByEmail(String email);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);


}