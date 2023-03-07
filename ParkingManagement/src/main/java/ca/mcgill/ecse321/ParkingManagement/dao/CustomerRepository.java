package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	Customer findCustomerByUsername(String username);
	Customer findCustomerByEmail(String email);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);


}