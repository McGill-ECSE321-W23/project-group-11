package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    /**
     * Method to validate payment for a spot
     * 
     * @param cardNumber a String that represents the customer's card number
     * @return true if the payment is valid
     * @throws Exception if any syntax error exists in the card number string 
     */
    @Transactional
    public boolean validatePayment(String cardNumber) throws Exception {

        if (cardNumber.isBlank()) {
            Exception e = new Exception("Card number must not be blank");
            throw e;
        }

        if (cardNumber.length() != 16) {
            Exception e = new Exception("Card number must be 16 digits long");
            throw e;
        }
        
        for(int i = 0; i<cardNumber.length(); i++) {
			if(!Character.isDigit(cardNumber.charAt(i))) {
				Exception e = new Exception("Card number must only contain numbers");
				throw e;
			}
        }
        return true;
    }

    /**
     * Method that calculates the price the customer needs to pay for the spot
     * 
     * @param spot  the particular spot that the customer is taking
     * @param hours the number of hours the customer is occupying the spot
     * @return the integer that represents the total cost that the customer has to
     *         pay
     */
    @Transactional
    public int getCalculatedPriceForSpot(TempSpotDto spot, int hours, SystemInfoDto sysInfo) {
        int price = 0;
        if (spot.getSize().equals(Size.Regular)) {
                price += sysInfo.getRegTempSpotPrice() * (hours * 4);
                return price;

        }

        if (spot.getSize().equals(Size.Large)) {
                price += sysInfo.getLargeTempSpotPrice() * (hours * 4);
                return price;
        }
        return price;
    }

}
