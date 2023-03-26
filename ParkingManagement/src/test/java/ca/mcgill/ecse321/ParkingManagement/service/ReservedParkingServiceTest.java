package ca.mcgill.ecse321.ParkingManagement.service;
// All imports recommended by tutorial notes 2.8.1-2.8.2
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ca.mcgill.ecse321.ParkingManagement.dao.CarRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.ReservedSpotRepository;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;
import ca.mcgill.ecse321.ParkingManagement.dto.ReservedSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.ReservedSpot;
import ca.mcgill.ecse321.ParkingManagement.model.Size;
import ca.mcgill.ecse321.ParkingManagement.utility.DtoConverters;


@ExtendWith(MockitoExtension.class)
public class ReservedParkingServiceTest {

    @Mock
    private ReservedSpotRepository reservedspotDao;

    @Mock
    private CarRepository carRepository; 

    @InjectMocks
    private ReservedParkingService service;
    
    private static final int SPOT_KEY = 300;
    private static final String CAR_KEY = "plate number";

    private static final int SPOT_KEY_2 = 301;
    private static final String CAR_KEY_2= "plate number";

    // these set mock outputs mimic repositories for proper unit testing
    @BeforeEach
    public void setMockOutput() {
        lenient().when(reservedspotDao.findByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SPOT_KEY)) {
                ReservedSpot spot = new ReservedSpot();
                spot.setPlaceNumber(SPOT_KEY);
                return spot;
            } else {
                return null;
            }
        });

        lenient().when(reservedspotDao.findByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SPOT_KEY_2)) {
                ReservedSpot spot = new ReservedSpot();
                spot.setPlaceNumber(SPOT_KEY_2);
                return spot;
            } else {
                return null;
            }
        });


        lenient().when(reservedspotDao.existsByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SPOT_KEY)) {
                return true;
            } else {
                return null;
            }
        });
        lenient().when(reservedspotDao.existsByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SPOT_KEY_2)) {
                return true;
            } else {
                return null;
            }
        });
       

        lenient().when(carRepository.findCarBylicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CAR_KEY)) {
                Car car = new Car();
                car.setLicensePlate(CAR_KEY);
                car.setSize(Size.Regular);
                return car;
            } else {
                return null;
            }
        });
        lenient().when(carRepository.findCarBylicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CAR_KEY_2)) {
                Car car = new Car();
                car.setLicensePlate(CAR_KEY_2);
                car.setSize(Size.Regular);
                return car;
            } else {
                return null;
            }
        });

        lenient().when(carRepository.existsBylicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CAR_KEY)) {
                return true;
            } else {
                return null;
            }
        });
        lenient().when(carRepository.existsBylicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CAR_KEY_2)) {
                return true;
            } else {
                return null;
            }
        });

        // Whenever anything is saved, just return the parameter object
            Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            	return invocation.getArgument(0);
            };
            lenient().when(reservedspotDao.save(any(ReservedSpot.class))).thenAnswer(returnParameterAsAnswer);
    }

    // ------------------------------------------- Create spot tests -----------------------------------------------------
    @Test
    public void testCreateReservedSpot() {
        String error = "";

        assertEquals(0, service.getAllReservedSpots().size());

        // set up variables for test
        Size size = Size.Regular;
        CarDto carDto = new CarDto(CAR_KEY, size);

        Car car = new Car();
        carRepository.save(car);

        int month = 12;
        int year = 2023; 

        ReservedSpotDto spot = null;

        try { // attempt service method 
            spot = service.createReservedSpot(carDto, year, month);
        } catch (Exception e) {
            error += e.getMessage();
        }


        assertEquals("", error); // check for errors

        // check outputs
        assertEquals(SPOT_KEY , spot.getPlaceNumber()); 
        assertEquals(CAR_KEY, spot.getCar().getLicensePlate());
        assertEquals(month, spot.getMonth());
        assertEquals(year, spot.getYear());
    }

    @Test
    public void testCreateTempSpotInvalidDate() {
        assertEquals(0, service.getAllReservedSpots().size());
        String error = "";
        int month = 12;
        int year = 2020;
        Size carSize = Size.Regular;
        CarDto carDto = new CarDto(CAR_KEY, carSize);

        try {
            service.createReservedSpot(carDto, year, month);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("Selected time cannot be in the past.", error);
    }

    @Test
    public void testCreateReservedSpotWithLargeCar() {
        assertEquals(0, service.getAllReservedSpots().size());
        String error = "";
        int month = 12;
        int year = 2023;
        Size carSize = Size.Large;
        CarDto carDto = new CarDto(CAR_KEY, carSize);

        try {
            service.createReservedSpot(carDto, year, month);
        } catch (Exception e) {
            error += e.getMessage();
        }

        assertEquals("Only regular sized cars can reserve a monthly spot.", error);
    }

    @Test
    public void testCreateReservedSpotWithInvalidLicensePlate() {
        String error = "";
        Size carSize = Size.Regular;
        CarDto carDto = new CarDto("invalid license plate", carSize);

        try {
            service.createReservedSpot(carDto, 2023, 4);
        } catch (Exception e) {
            error += e.getMessage();
        }

        assertEquals("Inputted licence plate does not match a car in the database.", error);
    }

    // @Test
    // public void testGetAllReservedSpots() throws Exception {

    //     Size size = Size.Regular;
    //     CarDto carDto1 = new CarDto(CAR_KEY, size);
    //     CarDto carDto2 = new CarDto(CAR_KEY_2, size);

    //     Car car1 = new Car();
    //     Car car2 = new Car();
      

    //     carRepository.save(car1);
    //     carRepository.save(car2);

    //     ReservedSpotDto spot1 = service.createReservedSpot(carDto1, 2023, 4);
    //     ReservedSpotDto spot2 = service.createReservedSpot(carDto2, 2023, 5);

    //     List<ReservedSpotDto> reservedSpots = service.getAllReservedSpots();

    //     assertEquals(2, reservedSpots.size());
    //     assertTrue(reservedSpots.contains(spot1));
    //     assertTrue(reservedSpots.contains(spot2));
    // }

    @Test
    public void testFindReservedSpotByPlaceNumber() throws Exception {
        CarDto carDto = new CarDto(CAR_KEY, Size.Regular);
        Car car = new Car();
        carRepository.save(car);
        ReservedSpotDto spot = service.createReservedSpot(carDto, 2023, 4);
        
        ReservedSpotDto foundSpot = service.getSpotByPlaceNumber(spot.getPlaceNumber());

        assertNotNull(foundSpot);
        assertEquals(spot, foundSpot);
    }
    
}

//TODO delete spot