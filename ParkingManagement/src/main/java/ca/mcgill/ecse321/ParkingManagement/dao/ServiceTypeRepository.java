package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.ServiceType;

public interface ServiceTypeRepository extends CrudRepository<ServiceType, String> {

	ServiceType findServiceTypeByName(String name);
	boolean existsByName(String name);
}