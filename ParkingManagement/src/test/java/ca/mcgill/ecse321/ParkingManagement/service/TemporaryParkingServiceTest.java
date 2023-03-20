package ca.mcgill.ecse321.ParkingManagement.service;
// All imports recommended by tutorial notes 2.8.1-2.8.2
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpot;


@ExtendWith(MockitoExtension.class)
public class TemporaryParkingServiceTest {

//TODO 2.8.2 step 3
@Mock
private LargeTempSpotRepository largeTempDao;
private RegularTempSpotRepository regTempDao;

@InjectMocks
private TemporaryParkingService service;

private static final int SPOT_KEY = 1;

@BeforeEach
public void setMockOutput() {
    lenient().when(largeTempDao.findById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
        if(invocation.getArgument(0).equals(SPOT_KEY)) {
            LargeTempSpot spot = new LargeTempSpot();
            spot.setId(SPOT_KEY);;
            return spot;
        } else {
            return null;
        }
    });
}




}
