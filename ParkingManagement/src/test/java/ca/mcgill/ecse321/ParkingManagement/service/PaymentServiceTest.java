package ca.mcgill.ecse321.ParkingManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private SystemInfoRepository systemInfoDao;

    @InjectMocks
    private PaymentService service;

    private final String cardNumber = "1234567898765432";
    private final int hours = 1;
    private final int id = 1;


    @Test
    public void testValidatePayment() {
        Boolean result = false;
        try {
            result = service.validatePayment(cardNumber);
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(true, result);
    }

    @Test
    public void testGetCalculatedPriceForSpot() {
        int result = 0;

        RegularTempSpot regSpot = new RegularTempSpot();
        result = service.getCalculatedPriceForSpot(regSpot, hours);
        assertNotEquals(0, result);
        assertEquals(systemInfoDao.findSystemInfoById(id).getRegTempSpotPrice() * hours * 4, result);
    }
}
