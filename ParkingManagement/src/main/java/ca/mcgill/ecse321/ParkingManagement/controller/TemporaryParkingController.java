package ca.mcgill.ecse321.ParkingManagement.controller;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.service.TemporaryParkingService;



@CrossOrigin(origins = "*")
@RestController
public class TemporaryParkingController {

    TemporaryParkingService tempSpotService = new TemporaryParkingService(); // TODO is this how this should work???
    
    @GetMapping(value = { "/tempspots", "/tempspots/" })
    public List<TempSpotDto> getAllTempSpots() {
	    return tempSpotService.getAllTempSpots().stream().collect(Collectors.toList());
    }



}
