package ca.mcgill.ecse321.ParkingManagement.controller;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;
import ca.mcgill.ecse321.ParkingManagement.dto.TempSpotDto;
import ca.mcgill.ecse321.ParkingManagement.service.TemporaryParkingService;


@CrossOrigin(origins = "*")
@RestController
public class TemporaryParkingController {

    @Autowired
    TemporaryParkingService tempSpotService = new TemporaryParkingService();
    
    // for testing purposes
    @GetMapping(value = {"/"})
    public String sayHello() {
        return "hello world";
    }

    /**
     * Returns all temporary spots
     * @return list of all temporary spots as DTOs
     */
    @GetMapping(value = { "/tempspots", "/tempspots/" })
    public ResponseEntity<?> getAllTempSpots() {
        List<TempSpotDto> spotList =  tempSpotService.getAllTempSpots().stream().collect(Collectors.toList());
        return new ResponseEntity<>(spotList, HttpStatus.OK);
    }

    /**
     * Returns a temporary spot with the inputted place number
     * @param placeNumber
     * @return ResponseEntity with DTO of spot with the place number or error message
     */
    @GetMapping(value = {"/tempspot/{placeNumber}", "/tempspot/{placeNumber}/"}) 
    public ResponseEntity<?> getTempSpotByPlaceNumber(@PathVariable("placeNumber") Integer placeNumber) {
        try {
            TempSpotDto spot = tempSpotService.getSpotByPlaceNumber(placeNumber);
            return new ResponseEntity<>(spot, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = { "/tempspot/book", "/tempspot/book/" }) 
    public ResponseEntity<?> bookTemporarySpot(@RequestBody TempSpotDto spotDto) {
        try {
            TempSpotDto spot = tempSpotService.createTempSpot(spotDto);
            return new ResponseEntity<>(spot, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage() + "Not Created", HttpStatus.BAD_REQUEST);
        }
    }

    // @PostMapping(value = { "/tempspot/addtime/{placeNumber}", "/tempspot/addtime/{placeNumber}/" }) // 
    // public TempSpotDto addTimeToTemporarySpot(@PathVariable("placeNumber") Integer placeNumber, 
    // @RequestParam Integer duration) throws Exception {
    //     return tempSpotService.editTempSpot(tempSpotService.getSpotByPlaceNumber(placeNumber), 0);
    // }
}
