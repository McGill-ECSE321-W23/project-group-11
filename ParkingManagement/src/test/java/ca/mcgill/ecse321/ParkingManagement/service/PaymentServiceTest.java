package ca.mcgill.ecse321.ParkingManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    SystemInfoRepository systemInfoDao;

    @Mock
    ServiceTypeRepository serviceTypeDao;

    @InjectMocks
    private PaymentService service;

    private final String cardNumber = "1234567898765432";
    private final String licensePlate = "123ABC";

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
    public void testGetCalculatedPriceForRegSpot() {
        int result = 0;
        TempSpotDto tempSpot = new TempSpotDto();
        CarDto car = new CarDto(licensePlate, Size.Regular);

        tempSpot.setCar(car);
        tempSpot.setDuration(4);

        SystemInfo sysInfo = new SystemInfo();
        sysInfo.setRegTempSpotPrice(15);

        when(systemInfoDao.findSystemInfoById(anyInt())).thenReturn(sysInfo);
        result = service.getCalculatedPriceForSpot(tempSpot);
        assertEquals(60, result);
    }

    @Test
    public void testGetCalculatedPriceForLargeSpot() {
        int result = 0;
        TempSpotDto tempSpot = new TempSpotDto();
        CarDto car = new CarDto(licensePlate, Size.Large);

        tempSpot.setCar(car);
        tempSpot.setDuration(4);

        SystemInfo sysInfo = new SystemInfo();
        sysInfo.setLargeTempSpotPrice(20);

        when(systemInfoDao.findSystemInfoById(anyInt())).thenReturn(sysInfo);
        result = service.getCalculatedPriceForSpot(tempSpot);
        assertEquals(80, result);
    }

    @Test
    public void testCreditCardInvalidCharacter() {
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
    public void testInvalidCreditCardLength() {
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
    public void testBlankCreditCard() {
        String invalidCard = "";
        var error = "";
        try {
            service.validatePayment(invalidCard);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals("Card number must not be blank", error);
    }

    @Test
    public void testGetPriceForMonthlySpot() {
        SystemInfo sysInfo = new SystemInfo();
        sysInfo.setReservedSpotPrice(50);
        when(systemInfoDao.findSystemInfoById(anyInt())).thenReturn(sysInfo);
        int price = service.getPriceForMonthlySpot();
        assertEquals(50, price);
    }

    @Test
    public void testGetPriceForService() {
        ServiceType serviceType = new ServiceType();
        serviceType.setCost(150);
        serviceType.setName("OilChange");
        try {
            when(serviceTypeDao.findServiceTypeByName(anyString())).thenReturn(serviceType);
            int price = service.getPriceForService(serviceType.getName());
            assertEquals(150, price);
        } catch (Exception e) {

        }

    }

}
