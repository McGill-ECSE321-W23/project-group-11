package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dao.ServiceTypeRepository;
import ca.mcgill.ecse321.ParkingManagement.dao.SystemInfoRepository;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    SystemInfoRepository sysInfo;

    @Autowired
    ServiceTypeRepository serviceType;

    /**
     * Method to validate payment for a spot
     * 
     * @param cardNumber A String that represents the customer's card number
     * @return True if the payment is valid
     * @throws Exception If any syntax error exists in the card number string
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

        for (int i = 0; i < cardNumber.length(); i++) {
            if (!Character.isDigit(cardNumber.charAt(i))) {
                Exception e = new Exception("Card number must only contain numbers");
                throw e;
            }
        }
        return true;
    }

    /**
     * Method that calculates the price the customer needs to pay for the spot
     * 
     * @param spot The particular spot that the customer is taking
     * @return The integer that represents the total cost that the customer has to
     *         pay
     */
    @Transactional
    public String getCalculatedPriceForSpot(TempSpotDto spot, int sysInfoId) {
        int price = 0;
        if (spot.getCarDto().getSize().equals(Size.Regular)) {
            price += sysInfo.findSystemInfoById(sysInfoId).getRegTempSpotPrice() * (spot.getDuration());
            return "$"+price;

        }

        if (spot.getCarDto().getSize().equals(Size.Large)) {
            price += sysInfo.findSystemInfoById(sysInfoId).getLargeTempSpotPrice() * (spot.getDuration());
            return "$"+price;
        }
        return "$"+price;
    }

    /**
     * Method to get the price for a monthly reserved spot
     * 
     * @return The price associated with the monthly spot
     */
    @Transactional
    public String getPriceForMonthlySpot(int sysInfoId) {
        int price = sysInfo.findSystemInfoById(sysInfoId).getReservedSpotPrice();
        return "$"+price;
    }

    /**
     * Method to get The price for a given service type
     * 
     * @param name The particular service type's name whose pricing information is
     *             needed
     * @return The price associated with the service
     * @throws Exception if the service type is null
     */
    @Transactional
    public String getPriceForService(String name) throws Exception {
        if (serviceType.findServiceTypeByName(name) != null) {
            int price = serviceType.findServiceTypeByName(name).getCost();
            return "$"+price;
        } else {
            Exception e = new Exception("Service Type is null");
            throw e;
        }

    }

}
