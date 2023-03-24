package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.ReservedSpot;

public interface ReservedSpotRepository extends CrudRepository<ReservedSpot, Integer> {

	ReservedSpot findReservedSpotById(int Id);
	boolean existsById(int Id);

}