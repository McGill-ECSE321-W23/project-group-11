package ca.mcgill.ecse321.ParkingManagement.controller;

// import java.util.List;
// import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.ParkingManagement.dto.CarDto;
import ca.mcgill.ecse321.ParkingManagement.service.CarService;

@CrossOrigin(origins = "*")
@RestController
public class CarController {

    @Autowired
    CarService carService = new CarService();
    
    /**
     * creates a car
     * @param carDto
     * @return response entity with either dto or error message as well as the http status
     */
    @PostMapping(value = { "/car/create", "/car/create/" }) 
    public ResponseEntity<?> createCar(@RequestBody CarDto carDto) {
        try {
            CarDto car = carService.createCar(carDto);
            return new ResponseEntity<>(car, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage() + "Not Created", HttpStatus.BAD_REQUEST);
        }
    }

    // /**
    //  * deletes a car
    //  * @param carDto
    //  * @return ResponseEntity with boolean or error message as well as HTTP status
    //  */
    // @DeleteMapping(value = {"/tempspot/endbooking", "/tempspot/endbooking/"}) 
    // public ResponseEntity<?> endTemporarySpotBooking(@RequestBody CarDto carDto) {
    //     try {
    //         boolean car = carService.deleteCar(carDto);
    //         return new ResponseEntity<>(car, HttpStatus.NO_CONTENT);
    //     } catch(Exception e) {
    //         return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    //     }
    // }

}
