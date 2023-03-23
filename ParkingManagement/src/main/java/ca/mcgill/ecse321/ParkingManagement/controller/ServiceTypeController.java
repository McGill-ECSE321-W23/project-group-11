package ca.mcgill.ecse321.ParkingManagement.controller;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ServiceTypeDto> getAllServiceTypes() {
	    List<ServiceTypeDto> serviceTypeList = new ArrayList<>();
        for(ServiceType serviceType : serviceTypeService.getAllServiceTypes()){
            serviceTypeList.add(convertToDto(serviceType));
        }
        return serviceTypeList;
    }

    /**
	 * Gets a serviceType.
	 *
	 * @return serviceType.
	 */

    @GetMapping(value = {"/servicetype/{name}", "/servicetype/{name}/"})
    public ServiceTypeDto getServiceType(@PathVariable String name) throws Exception{
        return convertToDto(serviceTypeService.getServiceTypeByName(name));
    }

    /**
	 * Creates a service type.
	 * @param serviceTypeDto to delete.
	 * @return service type created.
	 */

    @PostMapping(value = {"/servicetype", "/servicetype/"})
    public ServiceTypeDto createServiceType(@RequestBody ServiceTypeDto serviceTypeDto) throws Exception{
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
    public void deleteServiceType(@RequestBody ServiceTypeDto serviceTypeDto)  throws Exception{
        //convert to a serviceType
        ServiceType serviceType = serviceTypeDto.toModel();
        //call service
        serviceTypeService.removeServiceType(serviceType);
        //return
        return;
    }

    //converts a Servicetype into a servicetype dto (might be useful) 
    private ServiceTypeDto convertToDto(ServiceType serviceType) {
        ServiceTypeDto dto = new ServiceTypeDto(serviceType.getName(),serviceType.getCost(),serviceType.getDuration(),serviceType.getManager());
        return dto;
    }

}
