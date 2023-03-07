package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.Manager;

@Repository
public interface ManagerRepository extends CrudRepository<Manager, Integer> {

	Manager findManagerByUsername(String username);
	Manager findManagerByEmail(String email);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);


}