package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.SystemInfo;

@Repository
public interface SystemInfoRepository extends CrudRepository<SystemInfo, Integer> {
    SystemInfo findSystemInfoById(int id);
    boolean existsById(int id);
}
