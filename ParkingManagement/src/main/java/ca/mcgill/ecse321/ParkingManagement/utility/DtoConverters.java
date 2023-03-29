package ca.mcgill.ecse321.ParkingManagement.utility;

import ca.mcgill.ecse321.ParkingManagement.dto.ReservedSpotDto;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;

import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.Car;
import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.ReservedSpot;
import ca.mcgill.ecse321.ParkingManagement.model.Size;
import ca.mcgill.ecse321.ParkingManagement.model.TempSpot;

public class DtoConverters {

    public static TempSpotDto convertToTempSpotDto(TempSpot spot) {
        TempSpotDto dto;
        if (spot instanceof LargeTempSpot) {
            dto = new TempSpotDto(spot.getId(), spot.getPlaceNumber(), spot.getDuration(), spot.getDate(), spot.getStartTime(), convertToCarDto(spot.getCar()), Size.Large);
        } else {
            dto = new TempSpotDto(spot.getId(), spot.getPlaceNumber(), spot.getDuration(), spot.getDate(), spot.getStartTime(), convertToCarDto(spot.getCar()), Size.Regular);
        }
        return dto;
    }

    public static ReservedSpotDto convertToReservedSpotDto(ReservedSpot spot) {
        ReservedSpotDto dto;
        dto = new ReservedSpotDto(spot.getId(), spot.getPlaceNumber(), spot.getMonth(), spot.getYear(), convertToCarDto(spot.getCar()));
        return dto;
    }
    

    public static CarDto convertToCarDto(Car car) {
        CarDto dto = new CarDto(car.getLicensePlate(), car.getSize());
        return dto;
    }

}
