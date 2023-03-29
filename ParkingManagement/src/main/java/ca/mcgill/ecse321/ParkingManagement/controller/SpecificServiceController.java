package ca.mcgill.ecse321.ParkingManagement.controller;
import java.util.List;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ca.mcgill.ecse321.ParkingManagement.service.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;


@CrossOrigin(origins = "*")
@RestController
public class SpecificServiceController {

    @Autowired
    private SpecificServiceBookingService specificServiceBookingService;

    /**
	 * Gets a booking by Id.
	 *
	 * @return booking with given Id.
	 */
    @GetMapping(value = {"/booking/{id}", "/booking/{id}/"})
    public ResponseEntity<?> getBookingById(@PathVariable int id) throws Exception{
        try{
            return new ResponseEntity<>(convertToDto(specificServiceBookingService.getBookingById(id)),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /**
	 * Gets all bookings.
	 *
	 * @return all bookings.
	 */
     @GetMapping(value = {"/bookings", "/bookings/"})
     public ResponseEntity<?> getAllBookings() throws Exception{
        try{
            List<SpecificServiceDto> specificServiceList = new ArrayList<>();
            for(SpecificService specificService : specificServiceBookingService.getAllBookings()){
                specificServiceList.add(convertToDto(specificService));
            }
            return new ResponseEntity<>(specificServiceList,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /**
	 * Gets all bookings by a car.
	 *
	 * @return all bookings.
	 */
     @GetMapping(value = {"/bookings/{licencePlate}/", "/bookings/{licencePlate}"})
     public ResponseEntity<?> getBookingByLicencePlate(@PathVariable String licencePlate) throws Exception{
        try{
            List<SpecificServiceDto> specificServiceList = new ArrayList<>();
            for(SpecificService specificService : specificServiceBookingService.getAllBookingsByCar(licencePlate)){
                specificServiceList.add(convertToDto(specificService));
            }
            return new ResponseEntity<>(specificServiceList,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

     /**
	 * Delete booking by a car.
	 *
	 */
    @DeleteMapping(value = {"/booking/{id}","/booking/{id}/"})
    public ResponseEntity<?> deleteBookingById(@PathVariable int id) throws Exception{
        try{
            specificServiceBookingService.deleteBookingById(id);
            return new ResponseEntity<>("Booking Deleted", HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /**
	 * Creates a booking.
	 * @param specificServiceDto to create.
	 * @return booking created.
	 */
    @PostMapping(value = {"/booking","/booking/"})
    public ResponseEntity<?> createServiceBooking(@RequestBody SpecificServiceDto specificServiceDto) throws Exception{
        try{
            //convert to a specificService type
            SpecificService specificService = specificServiceDto.toModel();
            //cal service
            specificService = specificServiceBookingService.createBooking(specificService.getDate(), specificService.getStartTime(), specificService.getEmployee(), specificService.getCar(), specificService.getServiceType());
            //return dto
            return new ResponseEntity<>(convertToDto(specificService),HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } 
    } 
    
    /**
	 * Creates a booking.
	 * @param id of booking.
     * @param date of booking.
     * @param startTime of booking.
     * @param employee of booking.
	 * @return booking created.
	 */
    @PutMapping(value = {"/bookings/{id}","/bookings/{id}/"})
    public ResponseEntity<?> editBookingById(@PathVariable int id, @RequestBody Date date, @RequestBody LocalTime starTime, @RequestBody String employee) throws Exception{
        try{
            return new ResponseEntity<>(specificServiceBookingService.editBookingById(id,date,starTime,employee),HttpStatus.ACCEPTED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    private SpecificServiceDto convertToDto(SpecificService specificService) {
        SpecificServiceDto dto = new SpecificServiceDto(specificService.getId(),specificService.getDate(),specificService.getStartTime(),specificService.getEmployee(),specificService.getServiceType(),specificService.getCar());
        return dto;
    }
}