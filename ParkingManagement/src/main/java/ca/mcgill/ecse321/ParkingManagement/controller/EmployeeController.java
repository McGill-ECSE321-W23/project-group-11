package ca.mcgill.ecse321.ParkingManagement.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.ParkingManagement.service.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

@CrossOrigin(origins = "*")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 
     * get customer
     * 
     * 
     */
    @GetMapping(value = {"/employees","/employees/"})
    public ResponseEntity<?>  getAllEmployees() {
        List<EmployeeDto> accountList = new ArrayList<>();
        for (Employee employee : employeeService.getAllEmployees()) {
            accountList.add(convertToDto(employee));
        }
        return new ResponseEntity<>(accountList, HttpStatus.OK);   
        }
    
    /**
     * 
     * @param accountDto
     * @return
     */

    @PostMapping(value = {"/employee", "/employee/"})
    public ResponseEntity<?> createEmployee(@RequestBody AccountDto accountDto) {
        try{
            //convert to an Account
            Account account = accountDto.toModel();
            Employee employee = employeeService.createEmployee(account);
            return new ResponseEntity<>(convertToDto(employee), HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto(employee.getId(), employee.getAccount().toString());
        return dto;
    }

    @DeleteMapping(value = {"/employee/{email}", "/employee/{email}"})
    public ResponseEntity<?> deleteEmployee(@PathVariable String email) {
        try{
           Employee employee = employeeService.getEmployeeByEmail(email);
         employeeService.deleteEmployee(employee);
            return new ResponseEntity<>("Employee deleted", HttpStatus.NO_CONTENT);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
    
    
}
