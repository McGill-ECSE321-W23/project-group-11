package ca.mcgill.ecse321.ParkingManagement.controller;

import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ca.mcgill.ecse321.ParkingManagement.service.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

@CrossOrigin(origins = "*")
@RestController
public class SystemInfoController {
    
    @Autowired
    SystemInfoService systemInfoService;

    @GetMapping(value = {"/SystemInfo/create/{id}/{regPrice}/{largePrice}/{reservedPrice}/{openTime}/{closeTime}", "/SystemInfo/create/{id}/{regPrice}/{largePrice}/{reservedPrice}/{openTime}/{closeTime}/"})
    public ResponseEntity<?> createSystemInfo(@PathVariable(name="id") int id, @RequestBody ManagerDto managerDto, @PathVariable(name="openTime") Time openTime, @PathVariable(name="closeTime") Time closeTime, @PathVariable(name="regPrice") int regPrice, @PathVariable(name="largePrice") int largePrice, @PathVariable(name="reservedPrice") int reservedPrice) throws Exception{
        try{
            return new ResponseEntity<>(systemInfoService.createSystemInfo(id, managerDto, openTime, closeTime, regPrice, largePrice, reservedPrice), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = {"/SystemInfo/get/{id}", "/SystemInfo/get/{id}/"})
    public ResponseEntity<?> getSystemInfo(@PathVariable(name="id") int id){
        try{
            return new ResponseEntity<>(systemInfoService.getSystemInfoById(id), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = {"/SystemInfo/delete/{id}", "/SystemInfo/delete/{id}/"})
    public ResponseEntity<?> deleteSystemInfo(@PathVariable(name="id") int id){
        try{
            systemInfoService.deleteSystemInfo(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
