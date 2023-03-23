package ca.mcgill.ecse321.ParkingManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.ServiceTypeDto;

@Service
public class ServiceTypeService{
    @Autowired
    ServiceTypeRepository serviceTypeRepository;
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
        if(manager == null || name == null){
            Exception e = new Exception("Missing information about the service type, must have a cost, name, manager and duration");
            throw e;
        }
        //check if the manager is in the database
        if(managerRepository.findManagerByid(manager.getId())==null){
            Exception e = new Exception("Manager does not exist");
            throw e;
        }
        //all is good create a service type
        ServiceType serviceType = new ServiceType();
        //set attributes
        serviceType.setName(name);
        serviceType.setCost(cost);
        serviceType.setDuration(duration);
        serviceType.setManager(manager);

        return serviceTypeRepository.save(serviceType);
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
        if(serviceTypeRepository.findServiceTypeByName(serviceType.getName())!=null){
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
        if(serviceTypeRepository.findServiceTypeByName(name)!=null){
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
    public Iterable<ServiceType> getAllServiceTypes() {
		return serviceTypeRepository.findAll();
	}

    //TODO make an edit ServiceType if the time permits
}
