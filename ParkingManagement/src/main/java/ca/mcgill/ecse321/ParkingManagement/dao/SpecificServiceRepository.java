package ca.mcgill.ecse321.ParkingManagement.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.ServiceType;
import ca.mcgill.ecse321.ParkingManagement.model.SpecificService;
import ca.mcgill.ecse321.ParkingManagement.model.SpecificServiceId;

@Repository
public interface SpecificServiceRepository extends CrudRepository<SpecificService, SpecificServiceId> {

    SpecificService findSpecificServiceByServiceTypeAndCar(ServiceType serviceType, Car car);
    boolean existsByServiceTypeAndCar(ServiceType serviceType, Car car);
}

