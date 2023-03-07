package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.RegularTempSpot;

@Repository
public interface RegTempSpotRepository extends CrudRepository<RegularTempSpot, Integer> {

	RegularTempSpot findRegTempSpotByID(int ID);
	boolean existsByID(int ID);


}