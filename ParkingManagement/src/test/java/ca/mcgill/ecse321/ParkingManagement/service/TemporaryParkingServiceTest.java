package ca.mcgill.ecse321.ParkingManagement.service;
// All imports recommended by tutorial notes 2.8.1-2.8.2
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;import static org.mockito.Mockito.lenient;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
// import java.time.format.DateTimeFormatter;
// import java.util.Calendar;
// import java.util.ArrayList;
// import java.util.List;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
// import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ca.mcgill.ecse321.ParkingManagement.dao.CarRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.LargeTempSpotRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.RegularTempSpotRepository;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;
import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.RegularTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.Size;
import ca.mcgill.ecse321.ParkingManagement.utility.DtoConverters;


@ExtendWith(MockitoExtension.class)
public class TemporaryParkingServiceTest {

    @Mock
    private LargeTempSpotRepository largeTempDao;
    @Mock
    private RegularTempSpotRepository regTempDao;
    @Mock
    private CarRepository carRepository;


    @InjectMocks
    private TemporaryParkingService service;

    private static final int SPOT_KEY_LARGE = 1;
    private static final int SPOT_KEY_REG = 21;
    private static final int SPOT_KEY_REG_NULL = 22;
    private static final String CAR_KEY = "regular plate number";
    private static final String CAR_KEY_LARGE = "large plate number";

    // these set mock outputs mimic repositories for proper unit testing
    @BeforeEach
    public void setMockOutput() {
        lenient().when(largeTempDao.findByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SPOT_KEY_LARGE)) {
                LargeTempSpot spot = new LargeTempSpot();
                spot.setPlaceNumber(SPOT_KEY_LARGE);
                Car car = new Car();
                spot.setDuration(5);
                car.setLicensePlate(CAR_KEY_LARGE);
                spot.setCar(car);
                return spot;
            } else {
                return null;
            }
        });

        lenient().when(regTempDao.findByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SPOT_KEY_REG)) {
                RegularTempSpot spot = new RegularTempSpot();
                spot.setPlaceNumber(SPOT_KEY_REG);
                spot.setDuration(5);
                Car car = new Car();
                car.setLicensePlate(CAR_KEY);
                spot.setCar(car);
                return spot;
            } else {
                return null;
            }
        });

        lenient().when(regTempDao.existsByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SPOT_KEY_REG)) {
                return true;
            } else if(invocation.getArgument(0).equals(SPOT_KEY_REG_NULL)) {
                return false;
            } else {
                return false;
            }
        });
        lenient().when(largeTempDao.existsByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(SPOT_KEY_LARGE)) {
                return true;
            } else {
                return false;
            }
        });

        lenient().when(carRepository.findCarBylicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CAR_KEY_LARGE)) {
                Car car = new Car();
                car.setLicensePlate(CAR_KEY_LARGE);
                car.setSize(Size.Large);
                return car;
            } else if(invocation.getArgument(0).equals(CAR_KEY)) {
                Car car = new Car();
                car.setLicensePlate(CAR_KEY);
                car.setSize(Size.Regular);
                return car;
            } else {
                return null;
            }
        });

        lenient().when(carRepository.existsBylicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(CAR_KEY) || invocation.getArgument(0).equals(CAR_KEY_LARGE)) {
                return true;
            } else {
                return false;
            }
        });

        // Mock for testing findAllTempSpots()
        // Cannot be used with other unit tests as the findAllTempSpots() is used to verify no database activity at the start of each unit test
        // lenient().when(regTempDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
        //     List<TempSpotDto> spots = new ArrayList<TempSpotDto>();
        //     TempSpotDto tempSpot = new TempSpotDto();
        //     tempSpot.setPlaceNumber(SPOT_KEY_REG);
        //     spots.add(tempSpot);
        //     return spots;
        // });
        // lenient().when(largeTempDao.findAll()).thenAnswer( (InvocationOnMock invocation) -> {
        //     List<TempSpotDto> spots = new ArrayList<TempSpotDto>();
        //     TempSpotDto tempSpot = new TempSpotDto();
        //     tempSpot.setPlaceNumber(SPOT_KEY_LARGE);
        //     spots.add(tempSpot);
        //     return spots;
        // });
        

        // Whenever anything is saved, just return the parameter object
            Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            	return invocation.getArgument(0);
            };
            lenient().when(largeTempDao.save(any(LargeTempSpot.class))).thenAnswer(returnParameterAsAnswer);
            lenient().when(regTempDao.save(any(RegularTempSpot.class))).thenAnswer(returnParameterAsAnswer);
    }

    // ------------------------------------------- Create spot tests -------------------------------------------
    @Test
    public void testCreateTempSpot() {
        String error = "";

        //
        assertEquals(0, service.getAllTempSpots().size());

        // set up variables for test
        int duration = 5;
        String largeCarPlate = "large plate number";
        String regularCarPlate = "regular plate number";
        CarDto largeCarDto = new CarDto(largeCarPlate);
        CarDto regCarDto = new CarDto(regularCarPlate);

        Car largeCar = new Car(); 
        Car regCar = new Car();
        carRepository.save(largeCar); 
        carRepository.save(regCar);

        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);

        TempSpotDto largeSpotToCreate = new TempSpotDto(duration, date, time, largeCarDto);
        TempSpotDto regSpotToCreate = new TempSpotDto(duration, date, time, regCarDto);

        TempSpotDto largeSpot = null;
        TempSpotDto regSpot = null;
        try { // attempt service method
            largeSpot = service.createTempSpot(largeSpotToCreate);
            regSpot = service.createTempSpot(regSpotToCreate);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error); // check for errors

        // check outputs
        assertNotNull(largeSpot);
        assertEquals(Size.Large, largeSpot.getSize());
        // expected below is plus one because mock says that there is a spot with SPOT_KEY_LARGE already
        assertEquals(SPOT_KEY_LARGE + 1, largeSpot.getPlaceNumber()); 
        assertEquals(duration, largeSpot.getDuration());
        assertEquals(largeCarPlate, largeSpot.getCarDto().getLicensePlate());
        assertEquals(date, largeSpot.getDate());
        assertEquals(time, largeSpot.getStartTime());

        assertNotNull(regSpot);
        assertEquals(Size.Regular, regSpot.getSize());
        // expected below is plus one because mock says that there is a spot with SPOT_KEY_REG already
        assertEquals(SPOT_KEY_REG + 1, regSpot.getPlaceNumber());
        assertEquals(duration, regSpot.getDuration());
        assertEquals(regularCarPlate, regSpot.getCarDto().getLicensePlate());
        assertEquals(date, regSpot.getDate());
        assertEquals(time, regSpot.getStartTime());
    }

    @Test
    public void testCreateTempSpotInvalidDuration() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        int duration = 49;
        String carPlate = "regular plate number";
        Size carSize = Size.Regular;
        CarDto carDto = new CarDto(carPlate, carSize);
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);

        try {
            service.createTempSpot(new TempSpotDto(duration, date, time, carDto));
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("Inputted durration exceeds bounds of accepted values ([1, 48] intervals of 15 minutes).", error);
    }

    @Test
    public void testCreateTempSpotInvalidDateAndTime() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        int duration = 15;
        String carPlate = "regular plate number";
        Size carSize = Size.Regular;
        CarDto carDto = new CarDto(carPlate, carSize);
        // time and date are in the past
        Date date = new Date(2023/02/02);
        LocalTime time = LocalTime.of(0, 0);

        try {
            service.createTempSpot(new TempSpotDto(duration, date, time, carDto));
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("Start of attempted booking is in the past.", error);
    }

    // Test no longer needed: current implementation creates a new car if one did not previously exist
    @Test
    public void testCreateTempSpotNonexistentCar() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        int duration = 5;
        String carPlate = "bad plate number";
        Size carSize = Size.Regular;
        CarDto carDto = new CarDto(carPlate, carSize);
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);

        try {
            service.createTempSpot(new TempSpotDto(duration, date, time, carDto));
        } catch (Exception e) {
            error += e.getMessage();
        }
        // check for the expected error
        assertEquals("Cannot invoke \"ca.mcgill.ecse321.ParkingManagement.model.Car.getSize()\" because \"car\" is null", error);
    }

    // ------------------------------------------- Edit Spot Tests -------------------------------------------
    @Test
    public void testEditTempSpot() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        RegularTempSpot spot = new RegularTempSpot();
        int duration = 5;
        Car car = new Car();
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);
        spot.setCar(car);
        spot.setDuration(duration);
        spot.setDate(date);
        spot.setStartTime(time);
        spot.setPlaceNumber(SPOT_KEY_REG);

        TempSpotDto spotDto = new TempSpotDto(spot.getPlaceNumber(), 15, spot.getDate(), 
        spot.getStartTime(), DtoConverters.convertToCarDto(spot.getCar()));
        try {
            spotDto = service.editTempSpot(spotDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error);
        assertEquals(15, spotDto.getDuration());
    }

    @Test
    public void testEditTempSpotLarge() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        RegularTempSpot spot = new RegularTempSpot();
        int duration = 5;
        Car car = new Car();
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);
        spot.setCar(car);
        spot.setDuration(duration);
        spot.setDate(date);
        spot.setStartTime(time);
        spot.setPlaceNumber(SPOT_KEY_LARGE);

        TempSpotDto spotDto = new TempSpotDto(spot.getPlaceNumber(), 15, spot.getDate(), 
        spot.getStartTime(), DtoConverters.convertToCarDto(spot.getCar()));
        try {
            spotDto = service.editTempSpot(spotDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error);
        assertEquals(15, spotDto.getDuration());
    }
    
    @Test
    public void testEditTempSpotInvalidDuration() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        RegularTempSpot spot = new RegularTempSpot();
        int duration = 5;
        Car car = new Car();
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);
        spot.setCar(car);
        spot.setDuration(duration);
        spot.setDate(date);
        spot.setStartTime(time);
        spot.setPlaceNumber(SPOT_KEY_REG);

        TempSpotDto spotDto = new TempSpotDto(spot.getPlaceNumber(), 4, spot.getDate(), 
        spot.getStartTime(), DtoConverters.convertToCarDto(spot.getCar()));
        try {
            service.editTempSpot(spotDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("Inputted duration is less than or equal to existing duration.", error);
    }

    @Test
    public void testEditTempSpotInvalidDuration2() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        RegularTempSpot spot = new RegularTempSpot();
        int duration = 5;
        Car car = new Car();
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);
        spot.setCar(car);
        spot.setDuration(duration);
        spot.setDate(date);
        spot.setStartTime(time);
        spot.setPlaceNumber(SPOT_KEY_REG);

        TempSpotDto spotDto = new TempSpotDto(spot.getPlaceNumber(), -2, spot.getDate(), 
        spot.getStartTime(), DtoConverters.convertToCarDto(spot.getCar()));
        try {
            service.editTempSpot(spotDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("Inputted duration is out of accepted bounds.", error);
    }
    @Test
    public void testEditTempSpotInvalidSpot() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        try {
            service.editTempSpot(null);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("Inputted spot is null.", error);
    }

    // ------------------------------------------- End Reservation Tests -------------------------------------------

    @Test
    public void testDeleteTempSpot() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        boolean deleted = false;

        RegularTempSpot spot = new RegularTempSpot();
        int duration = 5;
        Car car = new Car();
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);
        spot.setCar(car);
        spot.setDuration(duration);
        spot.setDate(date);
        spot.setStartTime(time);
        spot.setPlaceNumber(SPOT_KEY_REG);

        regTempDao.save(spot);

        TempSpotDto spotDto = new TempSpotDto(spot.getPlaceNumber(), spot.getDuration(), spot.getDate(), 
        spot.getStartTime(), DtoConverters.convertToCarDto(spot.getCar()));
        try {
            deleted = service.deleteTempSpot(spotDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error);
        assertTrue(deleted);
    }

    @Test
    public void testDeleteTempSpotLarge() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        boolean deleted = false;

        RegularTempSpot spot = new RegularTempSpot();
        int duration = 5;
        Car car = new Car();
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);
        spot.setCar(car);
        spot.setDuration(duration);
        spot.setDate(date);
        spot.setStartTime(time);
        spot.setPlaceNumber(SPOT_KEY_LARGE);

        regTempDao.save(spot);

        TempSpotDto spotDto = new TempSpotDto(spot.getPlaceNumber(), spot.getDuration(), spot.getDate(), 
        spot.getStartTime(), DtoConverters.convertToCarDto(spot.getCar()));
        try {
            deleted = service.deleteTempSpot(spotDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error);
        assertTrue(deleted);
    }

    @Test
    public void testDeleteTempSpotNullSpot() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";
        boolean deleted = false;

        RegularTempSpot spot = new RegularTempSpot();
        int duration = 5;
        Car car = new Car();
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);
        spot.setCar(car);
        spot.setDuration(duration);
        spot.setDate(date);
        spot.setStartTime(time);
        spot.setPlaceNumber(SPOT_KEY_REG_NULL);

        regTempDao.save(spot);

        TempSpotDto spotDto = new TempSpotDto(spot.getPlaceNumber(), spot.getDuration(), spot.getDate(), 
        spot.getStartTime(), DtoConverters.convertToCarDto(spot.getCar()));
        try {
            deleted = service.deleteTempSpot(spotDto);
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("Inputted spot does not exist in database.", error);
        assertFalse(deleted);
    }

    // ------------------------------------------- Getter Tests -------------------------------------------

    @Test
    public void testGetTempSpotByPlaceNumber() {
        //assertEquals(0, service.getAllTempSpots().size());

        int duration = 5;
        CarDto carDtoRegular = new CarDto("regular plate number", Size.Regular);
        CarDto carDtoLarge = new CarDto("large plate number", Size.Large);
        Date date = Date.valueOf(LocalDate.now());
        LocalTime time = LocalTime.now().plusMinutes(1);

        String error = "";

        TempSpotDto largeSpot = null;
        TempSpotDto regSpot = null;
        try {
            // Tested for in previous test
            largeSpot = service.createTempSpot(new TempSpotDto(duration, date, time, carDtoLarge));
            regSpot = service.createTempSpot(new TempSpotDto(duration, date, time, carDtoRegular));
        } catch (Exception e) {
            fail();
        }
        largeSpot = null;
        regSpot = null;
        try {
            largeSpot = service.getSpotByPlaceNumber(1); // only 1 large spot in system, should be place number 1
            regSpot = service.getSpotByPlaceNumber(21);// only 1 regular spot in system, should be place number 21
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error);

        assertNotNull(largeSpot);
        assertEquals(1, largeSpot.getPlaceNumber());
        assertEquals(carDtoLarge.getLicensePlate(), largeSpot.getCarDto().getLicensePlate());

        assertNotNull(regSpot);
        assertEquals(21, regSpot.getPlaceNumber());
        assertEquals(carDtoRegular.getLicensePlate(), regSpot.getCarDto().getLicensePlate());
    }


    @Test
    public void testGetTempSpotByNonexistentPlaceNumber() {
        //assertEquals(0, service.getAllTempSpots().size());
        String error = "";

        TempSpotDto regSpot = null;
        try {
            regSpot = service.getSpotByPlaceNumber(40);// only 1 regular spot in system, should be place number 21
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("No temporary spot currently is reserved with that place number.", error);
        assertNull(regSpot);
    }



    // Cannot test this with mocks because it is used to verify that the database is inactive at the start of each unit test
    @Test
    public void testGetAllTempSpots() {
        String error = "";
        List<TempSpotDto> spotDtos = new ArrayList<TempSpotDto>();
        try {
            spotDtos =  service.getAllTempSpots(); // only 1 large spot in system, should be place number 1
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error);

        assertEquals(0, spotDtos.size());
    }

    @Test
    public void testGetAccountTempSpots() {
        String error = "";
        List<TempSpotDto> spotDtos = new ArrayList<TempSpotDto>();
        try {
            spotDtos =  service.getAllTempSpots(); // only 1 large spot in system, should be place number 1
        } catch (Exception e) {
            error += e.getMessage();
        }
        assertEquals("", error);

        assertEquals(0, spotDtos.size());
    }




}
