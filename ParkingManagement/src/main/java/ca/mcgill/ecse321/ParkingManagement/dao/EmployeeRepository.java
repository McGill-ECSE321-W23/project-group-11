package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

	Employee findEmployeeByUsername(String username);
	Employee findEmployeeByEmail(String email);
	Employee findEmployeeById(int id);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);


}