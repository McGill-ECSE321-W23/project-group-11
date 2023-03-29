package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Manager;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {
	Manager findManagerByid(Integer id);
	Manager findManagerByAccount(Account account);
	boolean existsByid(Integer id);
	void deleteManagerByAccount(Account account);
}