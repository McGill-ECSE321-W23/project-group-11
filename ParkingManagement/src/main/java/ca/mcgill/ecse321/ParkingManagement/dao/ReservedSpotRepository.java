package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.ReservedSpot;

@Repository
public interface ReservedSpotRepository extends CrudRepository<ReservedSpot, Integer> {

	ReservedSpot findReservedSpotById(int Id);
	boolean existsById(int Id);

}