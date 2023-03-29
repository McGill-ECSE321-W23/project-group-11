package ca.mcgill.ecse321.ParkingManagement.controller;
import java.util.List;
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
public class ServiceTypeController {

    @Autowired
    private ServiceTypeService serviceTypeService;
    /**
	 * Gets all service types.
	 *
	 * @return All servicetypes.
	 */

    @GetMapping(value = { "/servicetypes", "/servicetypes/" })
    public ResponseEntity<?> getAllServiceTypes() {
        List<ServiceTypeDto> serviceTypeList = new ArrayList<>();
        for(ServiceType serviceType : serviceTypeService.getAllServiceTypes()){
            serviceTypeList.add(convertToDto(serviceType));
        }
        return new ResponseEntity<>(serviceTypeList,HttpStatus.OK);
    }

    /**
	 * Gets a serviceType.
	 *
	 * @return serviceType.
	 */

    @GetMapping(value = {"/servicetype/{name}", "/servicetype/{name}/"})
    public ResponseEntity<?> getServiceType(@PathVariable String name) throws Exception{
        try{
            return new ResponseEntity<>(convertToDto(serviceTypeService.getServiceTypeByName(name)),HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /**
	 * Creates a service type.
	 * @param serviceTypeDto to delete.
	 * @return service type created.
	 */

    @PostMapping(value = {"/servicetype", "/servicetype/"})
    public ResponseEntity<?> createServiceType(@RequestBody ServiceTypeDto serviceTypeDto) throws Exception{
        try{
            //convert to a serviceType
            ServiceType serviceType = serviceTypeDto.toModel();
            //call service
            serviceType = serviceTypeService.createServiceType(serviceType.getName(),serviceType.getCost(),serviceType.getDuration(),serviceType.getManager());
            //return a dto
            return new ResponseEntity<>(convertToDto(serviceType),HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    /**
	 * Deletes a service type.
	 * @param serviceTypeDto to delete.
	 * @return void.
	 */

    @DeleteMapping(value = {"/servicetype/","/servicetype"})
    public ResponseEntity<?> deleteServiceType(@RequestBody ServiceTypeDto serviceTypeDto)  throws Exception{
        try{
            //convert to a serviceType
            ServiceType serviceType = serviceTypeDto.toModel();
            //call service
            serviceTypeService.removeServiceType(serviceType);
            //return
            return new ResponseEntity<>("Service Type Deleted", HttpStatus.NO_CONTENT);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //converts a Servicetype into a servicetype dto (might be useful) 
    private ServiceTypeDto convertToDto(ServiceType serviceType) {
        ServiceTypeDto dto = new ServiceTypeDto(serviceType.getName(),serviceType.getCost(),serviceType.getDuration(),serviceType.getManager());
        return dto;
    }

}
