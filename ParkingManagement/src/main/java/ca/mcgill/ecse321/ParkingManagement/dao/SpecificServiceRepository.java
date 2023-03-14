package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.sql.Date;

import ca.mcgill.ecse321.ParkingManagement.model.SpecificService;

@Repository
public interface SpecificServiceRepository extends CrudRepository<SpecificService, Integer> {

	SpecificService findSpecificServiceByDateAndTime(Date dateAndTime);
	boolean existsByDateAndTime(Date dateAndTime);
}