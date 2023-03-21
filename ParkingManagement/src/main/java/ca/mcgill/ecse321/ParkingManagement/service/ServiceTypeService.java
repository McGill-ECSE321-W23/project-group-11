package ca.mcgill.ecse321.ParkingManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.ServiceTypeDto;

@Service
public class ServiceTypeService{
    @Autowired
    ServiceType serviceTypeRepository;
    @Autowired
    ManagerRepository managerRepository;
    
    /**
     * Creates a ServiceType
     * @param name name of service you are creating
     * @param cost cost of the service
     * @param duration duration of the service
     * @param manager manager who is adding the service
     * @return service type created
     * @throws Exception
     */

     @Transactional
     public ServiceType createServiceType(String name, int cost, int duration, Manager manager) throws Exception{
        //check if manager is null
        if(manager == null){
            Exception e = new Exception("Inputted manager was null");
            throw e;
        }
        //check if the manager is in the database
        if(managerRepository.existsById(manager.getId())){
            Exception e = new Exception("Manager does not exist in database");
            throw e;
        }
        //all is good create a service type
        ServiceType serviceType = new ServiceType();
        //set attributes
        serviceType.setName(name);
        serviceType.setCost(cost);
        serviceType.setDuration(duration);
        serviceType.setManager(manager);

        return serviceType;
     }
     /**
     * Deletes an existing serviceType
     *
     * @param serviceType service type to delete
     * @return void
     * @throws Exception
     */
     @Transactional
     public void removeServiceType(ServiceType serviceType) throws Exception {
        //null service type check
        if(serviceType == null){
            Exception e = new Exception("Service Type is null");
            throw e;
        }
        //if it exists then delete it
        if(existsByName(serviceType.getName())){
            serviceTypeRepository.deleteById(serviceType.getName());
        }
        else{
            //if it doesnt exist throw exception
            Exception e = new Exception("Inputted service type doesn not exist.");
            throw e;
        }
    }
    /**
     * Returns a service type from a name
     *
     * @param name of desired spot
     * @return serivce with corresponding name
     * @throws Exception
     */
    @Transactional
    public ServiceType getServiceTypeByName(String name) throws Exception {
        if(name == null){
            Exception e = new Exception("Inputted name is null.");
            throw e;
        }
        ServiceType serviceType = null;
        //if the name exists then get it from database
        if(serviceTypeRepository.existsByName(name)){
            serviceType = serviceTypeRepository.findServiceTypeByName(name);
        }
        //if the name doesnt exist throw exception
        else{
            Exception e = new Exception("Service Type does not exist");
            throw e;
        }
        return serviceType;
    }
    /**
     * Returns a list of all service types
     *
     * @return list of TempSpots
     */
    @Transactional
    public List<ServiceType> getAllServiceTypes() throws Exception{
        ServiceTypeDto serviceTypeDto;
        List<ServiceType> allServiceTypes = new ArrayList<ServiceTypeDto>();
        //for each service type in the database add it to the list of service types
        for(ServiceType serviceType : serviceTypeRepository.findAll().get()){
            serviceTypeDto = convertToDto(serviceType);
            allServiceTypes.add(serviceTypeDto);
        }
        return allServiceTypes;
    }
    //converts a Servicetype into a servicetype dto
    private ServiceTypeDto convertToDto(ServiceType serviceType) {
        ServiceTypeDto dto = new ServiceTypeDto(serviceType.getName(),serviceType.getCost(),serviceType.getDuration(),serviceType.getManager());
        return dto;
    }
}
