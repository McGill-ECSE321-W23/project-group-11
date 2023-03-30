package ca.mcgill.ecse321.ParkingManagement.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.ParkingManagement.dao.CarRepository;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Size;
import ca.mcgill.ecse321.ParkingManagement.utility.DtoConverters;

@Service
public class CarService {
    
    @Autowired
    CarRepository carDao;


    /**
     * Creates a car with a plate number and size
     *
     * @param plateNumber license plate of car
     * @param size Size of car (regular or large)
     * @return DTO of car created
     * @throws Exception
     */
    // @Transactional
    // public CarDto createCar(String plateNumber, Size size) throws Exception{
    //     Car car = new Car();
    //     car.setLicensePlate(plateNumber);
    //     car.setSize(size);
    //     try {
    //         carDao.save(car);
    //     } catch(Exception e) {
    //         throw new Exception("Car could not be saved because: " + e.getMessage()+ " ");
    //     }
    //     return DtoConverters.convertToCarDto(car);
    // }


    
}
