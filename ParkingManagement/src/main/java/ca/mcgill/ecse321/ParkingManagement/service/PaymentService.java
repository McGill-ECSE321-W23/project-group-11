package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.RegularTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.TempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.SystemInfo;

public class PaymentService extends SystemInfo{

    /**
     * Method to validate payment for a spot
     * 
     * @param cardNumber a String that represents the customer's card number
     * @return true if the payment is valid
     * @throws Exception
     */
    public boolean validatePayment(String cardNumber) throws Exception {

        boolean payment = false;

        if (cardNumber.isBlank()) {
            Exception e = new Exception("Card number must not be blank");
            throw e;
        }

        if (cardNumber.length() != 16) {
            Exception e = new Exception("Card number must be 16 digits long");
            throw e;
        }
        try {
            Integer.parseInt(cardNumber);
            payment = true;
            return payment;
        } catch (NumberFormatException n) {
            Exception e = new Exception("Card number must only contain numbers");
            throw e;
        }
    }

    /**
     * Method that calculates the price the customer needs to pay for the spot
     * @param spot the particular spot that the customer is taking
     * @param hours the number of hours the customer is occupying the spot
     * @return the float that represents the total cost for the customer to pay
     */
    
    public int priceForTempSpot(TempSpot spot, int hours){
        int price = 0;
        if(spot instanceof RegularTempSpot){
            price += getRegTempSpotPrice()*(hours*4);
            return price;
        }

        if(spot instanceof LargeTempSpot){
            price += getLargeTempSpotPrice()*(hours*4);
            return price;
        }

        return price;
    }

}
