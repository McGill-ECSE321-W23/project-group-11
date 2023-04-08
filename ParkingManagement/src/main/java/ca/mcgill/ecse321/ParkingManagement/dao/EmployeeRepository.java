package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
	Employee findEmployeeById(int Id);
	boolean existsById(int Id);
	Employee findEmployeeByAccount(Account account);
	Employee deleteEmployeeByAccount(Account account);
}