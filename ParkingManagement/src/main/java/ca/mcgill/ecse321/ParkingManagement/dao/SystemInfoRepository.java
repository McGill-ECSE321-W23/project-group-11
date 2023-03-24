package ca.mcgill.ecse321.ParkingManagement.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.ParkingManagement.model.SystemInfo;

public interface SystemInfoRepository extends CrudRepository<SystemInfo, Integer> {
    SystemInfo findSystemInfoById(int id);
    boolean existsById(int id);
}
