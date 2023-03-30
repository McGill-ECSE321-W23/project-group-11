package ca.mcgill.ecse321.ParkingManagement.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.ParkingManagement.dto.ReservedSpotDto;
import ca.mcgill.ecse321.ParkingManagement.service.ReservedParkingService;



@CrossOrigin(origins = "*")
@RestController
public class ReservedParkingController {
    
    @Autowired
     ReservedParkingService reservedSpotService = new ReservedParkingService();
    
    // @GetMapping(value = { "/reservedspots", "/reservedspots/" })
    // public List<ReservedSpotDto> getAllReservedSpots() {
    //     return reservedSpotService.getAllReservedSpots().stream().collect(Collectors.toList());
    // }

    /**
     * @return list of all reserved spots as DTOs
     */
    @GetMapping(value = { "/reservedspots", "/reservedspots/" })
    public ResponseEntity<?> getAllReservedSpots() {
        List<ReservedSpotDto> spotList =  reservedSpotService.getAllReservedSpots().stream().collect(Collectors.toList());
        return new ResponseEntity<>(spotList, HttpStatus.OK);
    }


    // @GetMapping(value = {"/reservedspot/{placeNumber}", "/reservedspot/{placeNumber}/"}) 
    // public ReservedSpotDto getReservedSpotByPlaceNumber(@PathVariable("placeNumber") Integer placeNumber) throws Exception {
    //     return reservedSpotService.getSpotByPlaceNumber(placeNumber);
    // }

        /**
     * Returns a reserved spot with the inputted place number
     * @param placeNumber
     * @return ResponseEntity with DTO of spot with the place number or error message
     */
    @GetMapping(value = {"/reservedspot/{placeNumber}", "/reservedspot/{placeNumber}/"}) 
    public ResponseEntity<?> getReservedSpotByPlaceNumber(@PathVariable("placeNumber") Integer placeNumber) {
        try {
            ReservedSpotDto spot = reservedSpotService.getSpotByPlaceNumber(placeNumber);
            return new ResponseEntity<>(spot, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // @PostMapping(value = { "/reservedspot/book", "/reservedspot/book/" }) 
    // public ReservedSpotDto bookReserved(@RequestParam(name = "car") CarDto carDto, @RequestParam int year, @RequestParam Integer month) throws Exception {
    //     return reservedSpotService.createReservedSpot(carDto, year, month);
    // }
    @PostMapping(value = { "/reservedspot/book", "/reservedspot/book/" }) 
    public ResponseEntity<?> bookReservedSpot(@RequestBody ReservedSpotDto spotDto) {
        try {
            ReservedSpotDto spot = reservedSpotService.createReservedSpot(spotDto);
            return new ResponseEntity<>(spot, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage() + "Not Created", HttpStatus.BAD_REQUEST);
        }
    }

}
