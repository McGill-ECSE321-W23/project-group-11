package ca.mcgill.ecse321.ParkingManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private SystemInfoRepository systemInfoDao;

    @Mock
    private RegularTempSpotRepository regTempSpotDao;

    @InjectMocks
    private PaymentService service;

    private final String cardNumber = "1234567898765432";
    private final int hours = 1;
    private final int id = 1;
    private final int spotId = 301;

    @BeforeEach
    public void setMockOutput() {
        lenient().when(systemInfoDao.findSystemInfoById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(id)) {
                SystemInfo sys = new SystemInfo();
                sys.setId(id);
                sys.setRegTempSpotPrice(15);;
                return sys;
            } else {
                return null;
            }
        });

        lenient().when(regTempSpotDao.findByPlaceNumber(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(spotId)) {
                RegularTempSpot regSpot = new RegularTempSpot();
                regSpot.setPlaceNumber(spotId);
                regSpot.setDuration(hours);
                return regSpot;
            } else {
                return null;
            }
        });

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(regTempSpotDao.save(any(RegularTempSpot.class))).thenAnswer(returnParameterAsAnswer);
    }

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
        result = service.getCalculatedPriceForSpot(regTempSpotDao.findByPlaceNumber(spotId), hours, systemInfoDao.findSystemInfoById(id));
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
