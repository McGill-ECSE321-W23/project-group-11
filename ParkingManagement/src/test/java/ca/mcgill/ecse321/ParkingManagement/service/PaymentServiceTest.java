package ca.mcgill.ecse321.ParkingManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.ParkingManagement.dto.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {



    @InjectMocks
    private PaymentService service;

    private final String cardNumber = "1234567898765432";
    private final String licensePlate = "123ABC";
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
        TempSpotDto tempSpot = new TempSpotDto();
        CarDto car = new CarDto(licensePlate, Size.Regular);
        SystemInfoDto systemInfo = new SystemInfoDto();
        
        tempSpot.setCar(car);
        systemInfo.setRegTempSpotPrice(15);

        result = service.getCalculatedPriceForSpot(tempSpot, hours, systemInfo);
        assertEquals(60, result);
    }

    @Test
    public void testCreditCardInvalidCharacter(){
        String invalidCard = "1234567898765abc";
        var error = "";
        try {
            service.validatePayment(invalidCard);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals("Card number must only contain numbers", error);
    }

    @Test
    public void testInvalidCreditCardLength(){
        String invalidCard = "12345678987659879874654";
        var error = "";
        try {
            service.validatePayment(invalidCard);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals("Card number must be 16 digits long", error);
    }

    @Test 
    public void testBlankCreditCard(){
        String invalidCard = "";
        var error = "";
        try {
            service.validatePayment(invalidCard);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals("Card number must not be blank", error);
    }
}
