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
public class SystemInfoController {

    @Autowired
    private SystemInfoService systemInfoService;

    @PostMapping(value = { "/systeminfo/", "/systeminfo" }) 
    public ResponseEntity<?> createSystemInfo(@RequestBody SystemInfoDto systemInfoDto){
        try{
            SystemInfo systemInfo  = systemInfoService.createSystemInfo(systemInfoDto);
            return new ResponseEntity<>(systemInfo, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}