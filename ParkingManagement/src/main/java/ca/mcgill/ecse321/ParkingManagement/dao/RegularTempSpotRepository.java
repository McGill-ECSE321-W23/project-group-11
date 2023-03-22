package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.RegularTempSpot;

public interface RegularTempSpotRepository extends CrudRepository<RegularTempSpot, Integer> {

	RegularTempSpot findRegularTempSpotById(int id);
	boolean existsById(int id);
	boolean existsByPlaceNumber(int placeNumber);
	RegularTempSpot findByPlaceNumber(int placeNumber);

}