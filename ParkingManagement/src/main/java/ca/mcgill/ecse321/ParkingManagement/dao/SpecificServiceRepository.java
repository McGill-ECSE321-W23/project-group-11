package ca.mcgill.ecse321.ParkingManagement.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.SpecificService;

public interface SpecificServiceRepository extends CrudRepository<SpecificService, Integer> {


    SpecificService findSpecificServiceById(int id);
    boolean existsById(int id);

}

