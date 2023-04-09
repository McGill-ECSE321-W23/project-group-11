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
    // @Transactional
    // public boolean deleteSystemInfo(SystemInfo systemInfo)throws Exception{
    //     if(managerRepository.findManagerByid(systemInfo.getManager().getId())==null){
    //         Exception e = new Exception("Manager does not exist");
    //         throw e;
    //     }
    //     if(systemInfoRepository.findSystemInfoById(systemInfo.getId())==null){
    //         Exception e = new Exception("System Info does not exist");
    //         throw e;
    //     }
    //     else{
    //         systemInfoRepository.deleteById(systemInfo.getId());
    //         return true;
    //     }
    // }
    @Transactional
    public SystemInfo getSystemInfoById(int id){
        return systemInfoRepository.findSystemInfoById(id);
    }
    @Transactional
    public SystemInfo editSystemInfo(SystemInfoDto systemInfoDto) throws Exception{
        if(managerRepository.findManagerByid(systemInfoDto.getManager().getId())==null){
            Exception e = new Exception("Manager does not exist");
            throw e;
        }
        if(systemInfoRepository.findSystemInfoById(systemInfoDto.getId())==null){
            Exception e = new Exception("System Info does not exist");
            throw e;
        }
        else{
            SystemInfo systemInfo = systemInfoRepository.findSystemInfoById(systemInfoDto.getId());
            systemInfo.setOpenTime(systemInfoDto.getOpenTime());
            systemInfo.setCloseTime(systemInfoDto.getCloseTime());
            systemInfo.setLargeTempSpotPrice(systemInfoDto.getLargeTempSpotPrice());
            systemInfo.setRegTempSpotPrice(systemInfoDto.getRegTempSpotPrice());
            systemInfo.setReservedSpotPrice(systemInfoDto.getReservedSpotPrice());
            return systemInfoRepository.save(systemInfo);
        }
    }
}