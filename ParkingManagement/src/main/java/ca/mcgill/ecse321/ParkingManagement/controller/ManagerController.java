package ca.mcgill.ecse321.ParkingManagement.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.ParkingManagement.service.*;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

@CrossOrigin(origins = "*")
@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 
     * get Manager
     * 
     * 
     */
    @GetMapping(value = {"/manager","/manager/"})
    public ResponseEntity<?>  getAllManagers() {
        List<ManagerDto> accountList = new ArrayList<>();
        for (Manager manager : managerService.getAllManagers()) {
            accountList.add(convertToDto(manager));
        }
        return new ResponseEntity<>(accountList, HttpStatus.OK);   
        }

        private ManagerDto convertToDto(Manager manager) {
            ManagerDto dto = new ManagerDto(manager.getId(), manager.getAccount().toString());
            return dto;
        }
    
}
