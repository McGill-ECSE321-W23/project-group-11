package ca.mcgill.ecse321.ParkingManagement.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;


// import java.time.format.DateTimeFormatter;
// import java.util.Calendar;
// import java.util.ArrayList;
// import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
// import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
// import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ca.mcgill.ecse321.ParkingManagement.dao.CarRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.CustomerRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.EmployeeRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ManagerRepository;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Employee;
import ca.mcgill.ecse321.ParkingManagement.model.Size;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;
    @Mock
    private ManagerRepository managerRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private CustomerRepository customerRepository;


    @InjectMocks
    private CarService service;

    private final String CAR_KEY = "plate_number";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(carRepository.existsBylicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CAR_KEY)) {
                return true;
            } else {
                return false;
            }
        });

        lenient().when(carRepository.findCarBylicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CAR_KEY)) {
                Car car = new Car();
                car.setSize(Size.Regular);
                car.setLicensePlate(CAR_KEY);
                return car;
            } else {
                return null;
            }
        });

        lenient().when(employeeRepository.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
            return new ArrayList<Employee>();
        });
    
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(carRepository.save(any(Car.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateCar() {
        String error = "";
        CarDto carDto = new CarDto(CAR_KEY, Size.Regular);

        try {
            service.createCar(carDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error);
    }

    @Test
    public void testCreateCarInvalidInputs() {
        String error = "";
        CarDto carDto = new CarDto("", Size.Regular);
        try {
            service.createCar(carDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("Invalid car inputs", error);
    }

    @Test
    public void testDeleteCar() {
        String error = "";
        CarDto carDto = new CarDto(CAR_KEY, Size.Regular);
        try {
            service.deleteCar(carDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error);
    }

    @Test
    public void testDeleteNonexistentCar() {
        String error = "";
        CarDto carDto = new CarDto("nonexistent plate", Size.Regular);
        try {
            service.deleteCar(carDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("Car with this plate number does not exist", error);
    }
}
