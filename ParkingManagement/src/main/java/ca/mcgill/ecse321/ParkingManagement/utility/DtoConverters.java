package ca.mcgill.ecse321.ParkingManagement.utility;

import ca.mcgill.ecse321.ParkingManagement.dto.ReservedSpotDto;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;

import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.ReservedSpot;
import ca.mcgill.ecse321.ParkingManagement.model.TempSpot;

public class DtoConverters {

    public static TempSpotDto convertToTempSpotDto(TempSpot spot) {
        return new TempSpotDto(spot.getPlaceNumber(), spot.getDuration(), spot.getDate(), spot.getStartTime(), convertToCarDto(spot.getCar()));
    }

    public static ReservedSpotDto convertToReservedSpotDto(ReservedSpot spot) {
        ReservedSpotDto dto;
        dto = new ReservedSpotDto(spot.getId(), spot.getPlaceNumber(), spot.getMonth(), spot.getYear(), spot.getCar());
        return dto;
    }
    

    public static CarDto convertToCarDto(Car car) {
        CarDto dto = new CarDto(car.getLicensePlate(), car.getSize());
        return dto;
    }

}
