package ca.mcgill.ecse321.ParkingManagement.controller;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ServiceTypeDto> getAllServiceTypes() {
	    return service.getAllServiceTypes().stream().map(s -> convertToDto(s)).collect(Collectors.toList());
    }

    /**
	 * Gets a serviceType.
	 *
	 * @return serviceType.
	 */

    @GetMapping(value = {"/servicetype/{name}", "/servicetype/{name}/"})
    public ServiceTypeDto getServiceType(@PathVariable String name) {
        return convertToDto(serviceTypeService.getServiceTypeByName(name));
    }

    /**
	 * Creates a service type.
	 * @param serviceTypeDto to delete.
	 * @return service type created.
	 */

    @PostMapping(value = {"/servicetype", "/servicetype/"})
    public ServiceTypeDto createServiceType(@RequestBody ServiceTypeDto serviceTypeDto){
        //convert to a serviceType
        ServiceType serviceType = serviceTypeDto.toModel();
        //call service
        serviceType = serviceTypeService.createServiceType(serviceType.getName(),serviceType.getCost(),serviceType.getDuration(),serviceType.getManager());
        //return a dto
        return convertToDto(serviceType);
    }

    /**
	 * Deletes a service type.
	 * @param serviceTypeDto to delete.
	 * @return void.
	 */

    @DeleteMapping(value = {"/servicetype/","/servicetype"})
    public void deleteServiceType(@RequestBody ServiceTypeDto serviceTypeDto){
        //convert to a serviceType
        ServiceType serviceType = serviceTypeDto.toModel();
        //call service
        serviceType = serviceTypeService.removeServiceType(serviceType.getName());
        //return
        return;
    }

    //converts a Servicetype into a servicetype dto (might be useful) 
    private ServiceTypeDto convertToDto(ServiceType serviceType) {
        ServiceTypeDto dto = new ServiceTypeDto(serviceType.getName(),serviceType.getCost(),serviceType.getDuration(),serviceType.getManager());
        return dto;
    }

}
