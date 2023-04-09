package ca.mcgill.ecse321.ParkingManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

@Service
public class SystemInfoService {

    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    SystemInfoRepository systemInfoRepository;

    @Transactional
    public SystemInfo createSystemInfo(SystemInfoDto systemInfoDto)throws Exception{
        //get all attributes from the input and store them 
        Time openTime = systemInfoDto.getOpenTime();
        Time closeTime = systemInfoDto.getCloseTime();
        int largeTempSpotPrice = systemInfoDto.getLargeTempSpotPrice();
        int regTempSpotPrice = systemInfoDto.getRegTempSpotPrice();
        int reservedSpotPrice = systemInfoDto.getReservedSpotPrice();
        Manager manager = systemInfoDto.getManager();
        
        if(openTime==null||closeTime==null||manager==null){
            Exception e = new Exception("Cannot create SystemInfo without a valid open time, close time or manager");
            throw e;
        }
        if(managerRepository.findManagerByid(manager.getId())==null){
            Exception e = new Exception("Manager does not exist");
            throw e;
        }
        SystemInfo systemInfo = new SystemInfo(openTime,closeTime,largeTempSpotPrice,regTempSpotPrice,reservedSpotPrice,manager);
        return systemInfoRepository.save(systemInfo);
    }
}