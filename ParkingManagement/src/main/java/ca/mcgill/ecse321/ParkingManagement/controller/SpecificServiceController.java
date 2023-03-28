package ca.mcgill.ecse321.ParkingManagement.controller;
import java.util.List;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SpecificServiceDto getBookingById(@PathVariable int id) throws Exception{
        return convertToDto(specificServiceBookingService.getBookingById(id));
    }

    /**
	 * Gets all bookings.
	 *
	 * @return all bookings.
	 */
     @GetMapping(value = {"/bookings", "/bookings/"})
     public List<SpecificServiceDto> getAllBookings() throws Exception{
        List<SpecificServiceDto> specificServiceList = new ArrayList<>();
        for(SpecificService specificService : specificServiceBookingService.getAllBookings()){
            specificServiceList.add(convertToDto(specificService));
        }
        return specificServiceList;
    }

    /**
	 * Gets all bookings by a car.
	 *
	 * @return all bookings.
	 */
     @GetMapping(value = {"/bookings/{licencePlate}/", "/bookings/{licencePlate}"})
     public List<SpecificServiceDto> getBookingByLicencePlate(@PathVariable String licencePlate) throws Exception{
        List<SpecificServiceDto> specificServiceList = new ArrayList<>();
        for(SpecificService specificService : specificServiceBookingService.getAllBookingsByCar(licencePlate)){
            specificServiceList.add(convertToDto(specificService));
        }
        return specificServiceList;
    }

     /**
	 * Delete booking by a car.
	 *
	 */
    @DeleteMapping(value = {"/booking/{id}","/booking/{id}/"})
    public void deleteBookingById(@PathVariable int id) throws Exception{
        specificServiceBookingService.deleteBookingById(id);
        return;
    }

    /**
	 * Creates a booking.
	 * @param specificServiceDto to create.
	 * @return booking created.
	 */
    @PostMapping(value = {"/booking","/booking/"})
    public SpecificServiceDto createServiceBooking(@RequestBody SpecificServiceDto specificServiceDto) throws Exception{
        //convert to a specificService type
        SpecificService specificService = specificServiceDto.toModel();
        //cal service
        specificService = specificServiceBookingService.createBooking(specificService.getDate(), specificService.getStartTime(), specificService.getEmployee(), specificService.getCar(), specificService.getServiceType());
        //return dto
        return convertToDto(specificService); 
    } 
    
    /**
	 * Creates a booking.
	 * @param id of booking.
     * @param date of booking.
     * @param startTime of booking.
     * @param employee of booking.
	 * @return booking created.
	 */
    @PostMapping(value = {"/bookings/{id}","/bookings/{id}/"})
    public void editBookingById(@PathVariable int id, @RequestBody Date date, @RequestBody LocalTime starTime, @RequestBody String employee) throws Exception{
        specificServiceBookingService.editBookingById(id,date,starTime,employee);
        return;
    }

    private SpecificServiceDto convertToDto(SpecificService specificService) {
        SpecificServiceDto dto = new SpecificServiceDto(specificService.getId(),specificService.getDate(),specificService.getStartTime(),specificService.getEmployee(),specificService.getServiceType(),specificService.getCar());
        return dto;
    }
}