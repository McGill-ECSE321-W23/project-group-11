package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.Manager;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {
	Manager findManagerByid(Integer id);
	boolean existsByid(Integer id);
}