package ca.mcgill.ecse321.ParkingManagement.service;
// All imports recommended by tutorial notes 2.8.1-2.8.2
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import ca.mcgill.ecse321.ParkingManagement.dao.LargeTempSpotRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.RegularTempSpotRepository;
import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.RegularTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.Size;


@ExtendWith(MockitoExtension.class)
public class TemporaryParkingServiceTest {

//TODO 2.8.2 step 3
@Mock
private LargeTempSpotRepository largeTempDao;
@Mock
private RegularTempSpotRepository regTempDao;

@InjectMocks
private TemporaryParkingService service;

private static final int SPOT_KEY_LARGE = 1;
private static final int SPOT_KEY_REG = 21;

@BeforeEach
public void setMockOutput() {
    lenient().when(largeTempDao.findByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
        if(invocation.getArgument(0).equals(SPOT_KEY_LARGE)) {
            LargeTempSpot spot = new LargeTempSpot();
            spot.setPlaceNumber(SPOT_KEY_LARGE);
            return spot;
        } else {
            return null;
        }
    });

    lenient().when(regTempDao.findByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
        if(invocation.getArgument(0).equals(SPOT_KEY_REG)) {
            RegularTempSpot spot = new RegularTempSpot();
            spot.setPlaceNumber(SPOT_KEY_REG);
            return spot;
        } else {
            return null;
        }
    });

    // Whenever anything is saved, just return the parameter object TODO i don't actually know what this does
		// Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
		// 	return invocation.getArgument(0);
		// };
		// lenient().when(largeTempDao.save(any(LargeTempSpot.class))).thenAnswer(returnParameterAsAnswer);
        // lenient().when(regTempDao.save(any(RegularTempSpot.class))).thenAnswer(returnParameterAsAnswer);
}

@Test
	public void testCreateTempSpot() {
		assertEquals(0, service.getAllTempSpots().size());
        int duration = 5;
        Car car = new Car();
        Date date = new Date(2023/02/02);
        LocalTime time = LocalTime.of(9, 30);

        TempSpotDto largeSpot = null;
        TempSpotDto regSpot = null;
		try {
			largeSpot = service.createTempSpot(Size.Large, duration, car, date, time);
            regSpot = service.createTempSpot(Size.Regular, duration, car, date, time);
		} catch (Exception e) {
			// Check that no error occurred
			fail();
		}
		assertNotNull(largeSpot);
		assertEquals(Size.Large, largeSpot.getSize());
        assertNotNull(regSpot);
        assertEquals(Size.Regular, regSpot.getSize());	
    }




}
