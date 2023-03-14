package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.ParkingManagementSystem;

@Repository
public interface ParkingManagementSystemRepository extends CrudRepository<ParkingManagementSystem, Integer> {
    ParkingManagementSystem findParkingManagementSystemById(int id);
    boolean existsById(int id);
}