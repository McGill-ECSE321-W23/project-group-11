package ca.mcgill.ecse321.ParkingManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

public class PaymentServiceTest {

    private PaymentService service;
    private SystemInfoRepository systemInfo;
    private RegularTempSpot regSpot;

    private final String cardNumber = "1234567898765432";
    private final int hours = 1;

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
        result = service.getCalculatedPriceForSpot(regSpot, hours);
        assertNotEquals(0, result);
        assertEquals(systemInfo.findSystemInfoById(1).getRegTempSpotPrice() * hours * 4, result);
    }
}
