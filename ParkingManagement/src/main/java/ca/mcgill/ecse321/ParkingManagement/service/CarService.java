package ca.mcgill.ecse321.ParkingManagement.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.utility.DtoConverters;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    
    @Autowired
    CarRepository carDao;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ManagerRepository managerRepository;



    /**
     * Creates a car according to the DTO
     *
     * @param carDto
     * @return DTO of car created
     * @throws Exception
     */
    @Transactional
    public CarDto createCar(CarDto carDto) throws Exception{
        // input validation
        if (carDto.getLicensePlate() == "" || (carDto.getSize() != Size.Regular && carDto.getSize() != Size.Large)) {
            throw new Exception("Invalid car inputs");
        }
        Car car = new Car();
        car.setLicensePlate(carDto.getLicensePlate());
        car.setSize(carDto.getSize());
        car.setCustomer(carDto.getCustomer());
        carDao.save(car);
        return DtoConverters.convertToCarDto(car);
    }


    /**
     * Deletes a car with a license plate
     *
     * @param carDto DTO with license plate of car to be deleted
     * @throws Exception
     */
    @Transactional
    public boolean deleteCar(CarDto carDto) throws Exception{
        String plateNumber = carDto.getLicensePlate();
        if (!carDao.existsBylicensePlate(plateNumber)) {
            throw new Exception("Car with this plate number does not exist");
        }
        Car car = carDao.findCarBylicensePlate(plateNumber);
        carDao.delete(car);
        return true;
    }

    @Transactional
    public Iterable<CarDto> getAllCars(){
        List<CarDto> carList = new ArrayList<>();
        for(Car car: carDao.findAll()){
            carList.add(DtoConverters.convertToCarDto(car));
        }
        return carList;
    }

    @Transactional
    public Iterable<CarDto> getAllCarsByCustomer(String email){
        List<CarDto> carList = new ArrayList<>();
        for(Car car:carDao.findAll()){
            for(Customer customer: customerRepository.findAll()){
                if(car.getCustomer().getAccount().getEmail().equals(email)){
                    carList.add(DtoConverters.convertToCarDto(car));
                }
            }
        }
        return carList;
    }
}
