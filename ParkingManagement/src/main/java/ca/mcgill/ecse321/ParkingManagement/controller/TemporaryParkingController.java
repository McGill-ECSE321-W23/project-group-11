package ca.mcgill.ecse321.ParkingManagement.controller;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping(value = { "/tempspots", "/tempspots/" })
    public List<TempSpotDto> getAllTempSpots() {
        return tempSpotService.getAllTempSpots().stream().collect(Collectors.toList());
    }

    @GetMapping(value = {"/tempspot/{placeNumber}", "/tempspot/{placeNumber}/"}) 
    public TempSpotDto getTempSpotByPlaceNumber(@PathVariable("placeNumber") Integer placeNumber) throws Exception {
        return tempSpotService.getSpotByPlaceNumber(placeNumber);
    }

    @PostMapping(value = { "/tempspot/book", "/tempspot/book/" }) // TODO not 100% how request parameters work, need to verify
    public TempSpotDto bookTemporarySpot(@RequestParam(name = "car") CarDto carDto, @RequestParam Date date,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME, pattern = "HH:mm") LocalTime startTime,
    @RequestParam Integer duration) throws Exception {
        return tempSpotService.createTempSpot(duration, carDto, date, startTime);
    }

    @PostMapping(value = { "/tempspot/addtime/{placeNumber}", "/tempspot/addtime/{placeNumber}/" }) // 
    public TempSpotDto addTimeToTemporarySpot(@PathVariable("placeNumber") Integer placeNumber, 
    @RequestParam Integer duration) throws Exception {
        return tempSpotService.editTempSpot(tempSpotService.getSpotByPlaceNumber(placeNumber), 0);
    }
}
