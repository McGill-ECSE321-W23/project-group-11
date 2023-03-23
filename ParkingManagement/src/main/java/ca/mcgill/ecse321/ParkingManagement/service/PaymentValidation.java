package ca.mcgill.ecse321.ParkingManagement.service;

public class PaymentValidation {

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
}
