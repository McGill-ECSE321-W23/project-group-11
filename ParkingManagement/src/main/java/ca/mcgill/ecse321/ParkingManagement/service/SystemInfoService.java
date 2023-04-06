package ca.mcgill.ecse321.ParkingManagement.service;

import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dao.SystemInfoRepository;
import ca.mcgill.ecse321.ParkingManagement.dto.ManagerDto;
import ca.mcgill.ecse321.ParkingManagement.dto.SystemInfoDto;

import java.sql.Time;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemInfoService{

    @Autowired
    SystemInfoRepository systemInfoRepository;

    /**
     * Creates a SystemInfo instance
     * @param id The id for the SystemInfo
     * @param managerDto The managerDto associated with the SystemInfo
     * @param openTime The opening time of the parking
     * @param closeTime The closing time of the parking
     * @param regPrice The price for a regular temp spot
     * @param largePrice The price for a large temp spot
     * @param reservedPrice The price for a monthly reserved spot
     * @return The saved SystemInfo instance
     * @throws Exception If something prevents the instance from being created
     */
    @Transactional
    public SystemInfo createSystemInfo(int id, ManagerDto managerDto, Time openTime, Time closeTime, int regPrice, int largePrice, int reservedPrice) throws Exception{
        //check if manager is not null
        if(managerDto == null){
            Exception e = new Exception("Cannot create a SystemInfo with a null manager");
            throw e;
        //check if systeminfo with the same id already exists
        }
        if(systemInfoRepository.findSystemInfoById(id)!= null){
            Exception e = new Exception("Cannot create a SystemInfo. Id already exists");
            throw e;
        }
        //check if any systeminfo already exists to make sure only one instance exists at any time
        if(systemInfoRepository.findAll()!= null){
            Exception e = new Exception("Cannot create a SystemInfo. An instance already exists");
            throw e;
        }
        //check if id is less than 1
        if(id < 1){
            Exception e = new Exception("Id cannot be less than 1 for a SystemInfo");
            throw e;
        }
        //check if any of the prices is negative
        if(regPrice < 0){
            Exception e = new Exception("regPrice cannot be set to a negative number");
            throw e;
        }
        if(largePrice < 0){
            Exception e = new Exception("largePrice cannot be set to a negative number");
            throw e;
        }
        if(reservedPrice < 0){
            Exception e = new Exception("reservedPrice cannot be set to a negative number");
            throw e;
        }

        //Create new systemInfo object and set its attributes
        SystemInfoDto systemInfo = new SystemInfoDto();
        systemInfo.setOpenTime(openTime);
        systemInfo.setCloseTime(closeTime);
        systemInfo.setManager(managerDto);
        systemInfo.setRegTempSpotPrice(regPrice);
        systemInfo.setLargeTempSpotPrice(largePrice);
        systemInfo.setReservedSpotPrice(reservedPrice);

        return systemInfoRepository.save(systemInfo);

    }

    /**
     * Gets a SystemInfo from a given id
     * @param id The id of the SystemInfo 
     * @return The SystemInfo object with the specified id
     * @throws Exception If no SystemInfo with the given id has been found
     */
    @Transactional
    public SystemInfo getSystemInfoById(int id) throws Exception{

        SystemInfo systemInfo = systemInfoRepository.findSystemInfoById(id);

        //Check if the returned systemInfo is null
        if(systemInfo == null){
            Exception e = new Exception("No SystemInfo exists with this id");
            throw e;
        }

        return systemInfo;

    }

    /**
     * Deletes a SystemInfo with a given id 
     * @param id The id of the SystemInfo instance to be deleted
     * @throws Exception If the instance could not be deleted or if the SystemInfo instance could not be found
     */
    @Transactional
    public void deleteSystemInfo(int id) throws Exception{
        //If systemInfo exists, delete it and check if it has been removed
        if(systemInfoRepository.existsById(id)){
            systemInfoRepository.deleteSystemInfoById(id);
            if(systemInfoRepository.existsById(id)){
                Exception e = new Exception("SystemInfo has not been deleted");
                throw e;
            }
        }
        //otherwise SystemInfo with the given id does not exist, throw exception
        else{
            Exception e = new Exception("SystemInfo with the specified id does not exist");
            throw e;
        }
    }

}
