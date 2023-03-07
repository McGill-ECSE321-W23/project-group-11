package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.ServiceType;

@Repository
public interface ServiceTypeRepository extends CrudRepository<ServiceType, Integer> {

	ServiceType findServiceTypeById(int ID);
	ServiceType findServiceTypeByName(String name);
	boolean existsById(int Id);
	boolean existsByName(String name);


}