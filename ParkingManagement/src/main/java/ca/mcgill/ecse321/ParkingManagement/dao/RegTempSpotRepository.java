package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.RegTempSpot;

@Repository
public interface RegTempSpotRepository extends CrudRepository<RegTempSpot, Integer> {

	RegTempSpot findRegTempSpotByID(int ID);
	boolean existsByID(int ID);


}