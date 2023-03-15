package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String> {
	Employee findEmployeeByEmail(String email);
	boolean existsByEmail(String email);
}