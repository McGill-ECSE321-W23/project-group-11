package ca.mcgill.ecse321.ParkingManagement.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private ReservedParkingService reservedParkingService;

    /**
     * Returns a list of all reserved spots as DTOs
     */
    @GetMapping("/reservedspots")
    public ResponseEntity<List<ReservedSpotDto>> getAllReservedSpots() {
        List<ReservedSpotDto> spotList = reservedParkingService.getAllReservedSpots().stream()
                .collect(Collectors.toList());
        return ResponseEntity.ok(spotList);
    }

    /**
     * Returns a reserved spot with the inputted place number
     */
    @GetMapping("/reservedspot/{placeNumber}")
    public ResponseEntity<?> getReservedSpotByPlaceNumber(@PathVariable Integer placeNumber) {
        if (placeNumber == null) {
            return ResponseEntity.badRequest().body("Place number cannot be null.");
        }
        try {
            ReservedSpotDto spot = reservedParkingService.getSpotByPlaceNumber(placeNumber);
            return ResponseEntity.ok(spot);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Creates a new reserved spot
     */
    @PostMapping("/reservedspot/book")
    public ResponseEntity<?> createReservedSpot(@RequestBody ReservedSpotDto spotDto) {
        try {
            ReservedSpotDto spot = reservedParkingService.createReservedSpot(spotDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(spot);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Deletes a reserved spot by ID
     */
    @DeleteMapping(value = {"/reservedspot/endbooking", "/reservedspot/endbooking/"}) 
    public ResponseEntity<?> endReservedSpotBooking(@RequestBody ReservedSpotDto spotDto) {
        try {
            boolean spot = reservedParkingService.deleteSpot(spotDto);
            return new ResponseEntity<>(spot, HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
