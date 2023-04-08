package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.Account;
import ca.mcgill.ecse321.ParkingManagement.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	Customer findCustomerByid(Integer id);
	Customer findCustomerByAccount(Account account);
	boolean existsByid(Integer id);
	void deleteCustomerByAccount(Account account);
}