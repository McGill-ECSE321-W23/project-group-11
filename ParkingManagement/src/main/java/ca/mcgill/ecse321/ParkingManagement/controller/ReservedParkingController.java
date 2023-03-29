package ca.mcgill.ecse321.ParkingManagement.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;
import ca.mcgill.ecse321.ParkingManagement.dto.ReservedSpotDto;
import ca.mcgill.ecse321.ParkingManagement.service.ReservedParkingService;



@CrossOrigin(origins = "*")
@RestController
public class ReservedParkingController {
    
    @Autowired
    private ReservedParkingService reservedSpotService;
    

    @GetMapping(value = { "/reservedspots", "/reservedspots/" })
    public List<ReservedSpotDto> getAllReservedSpots() {
        return reservedSpotService.getAllReservedSpots().stream().collect(Collectors.toList());
    }

    @GetMapping(value = {"/reservedspot/{placeNumber}", "/reservedspot/{placeNumber}/"}) 
    public ReservedSpotDto getReservedSpotByPlaceNumber(@PathVariable("placeNumber") Integer placeNumber) throws Exception {
        return reservedSpotService.getSpotByPlaceNumber(placeNumber);
    }

    @PostMapping(value = { "/reservedspot/book", "/reservedspot/book/" }) // TODO not 100% how request parameters work, need to verify
    public ReservedSpotDto bookReserved(@RequestParam(name = "car") CarDto carDto, @RequestParam int year, @RequestParam Integer month) throws Exception {
        return reservedSpotService.createReservedSpot(carDto, year, month);
    }

}
