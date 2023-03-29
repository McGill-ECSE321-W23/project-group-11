package ca.mcgill.ecse321.ParkingManagement.utility;

import ca.mcgill.ecse321.ParkingManagement.dto.ReservedSpotDto;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;

import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.Customer;
import ca.mcgill.ecse321.ParkingManagement.model.RegularTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.ReservedSpot;
import ca.mcgill.ecse321.ParkingManagement.model.TempSpot;

public class DtoConverters {

    public static TempSpotDto convertToTempSpotDto(TempSpot spot) {
        if (spot instanceof RegularTempSpot) {
            RegularTempSpot regSpot = (RegularTempSpot) spot;
            return new TempSpotDto(regSpot.getPlaceNumber(), regSpot.getDuration(), regSpot.getDate(), regSpot.getStartTime(), convertToCarDto(regSpot.getCar()));
        } else { 
            LargeTempSpot largeSpot = (LargeTempSpot) spot;
            return new TempSpotDto(largeSpot.getPlaceNumber(), largeSpot.getDuration(), largeSpot.getDate(), largeSpot.getStartTime(), convertToCarDto(largeSpot.getCar()));
        }
        
    }

    public static ReservedSpotDto convertToReservedSpotDto(ReservedSpot spot) {
        ReservedSpotDto dto;
        dto = new ReservedSpotDto(spot.getId(), spot.getPlaceNumber(), spot.getMonth(), spot.getYear(), convertToCarDto(spot.getCar()));
        return dto;
    }
    

    public static CarDto convertToCarDto(Car car) {
        CarDto dto = new CarDto(car.getLicensePlate(), car.getSize());
        Customer customer = car.getCustomer();
        if (customer != null) {
            dto.setCustomer(customer);
        }
        return dto;
    }

}
