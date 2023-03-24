package ca.mcgill.ecse321.ParkingManagement.utility;

import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.model.LargeTempSpot;
import ca.mcgill.ecse321.ParkingManagement.model.Size;
import ca.mcgill.ecse321.ParkingManagement.model.TempSpot;

public class DtoConverters {




    public static TempSpotDto convertToTempSpotDto(TempSpot spot) {
        TempSpotDto dto;
        if (spot instanceof LargeTempSpot) {
            dto = new TempSpotDto(spot.getId(), spot.getPlaceNumber(), spot.getDuration(), spot.getDate(), spot.getStartTime(), spot.getCar(), Size.Large);
        } else {
            dto = new TempSpotDto(spot.getId(), spot.getPlaceNumber(), spot.getDuration(), spot.getDate(), spot.getStartTime(), spot.getCar(), Size.Regular);
        }
        return dto;
    }
    
}
