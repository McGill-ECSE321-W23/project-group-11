package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	Customer findCustomerByid(Integer id);
	boolean existsByid(Integer id);
}