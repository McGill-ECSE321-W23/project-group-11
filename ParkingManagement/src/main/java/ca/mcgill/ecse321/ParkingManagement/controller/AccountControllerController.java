// package ca.mcgill.ecse321.ParkingManagement.controller;

// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
// import ca.mcgill.ecse321.ParkingManagement.service.AccountService;
// import ca.mcgill.ecse321.ParkingManagement.dao.AccountRepository;
// import ca.mcgill.ecse321.ParkingManagement.dto.AccountDto;
// import java.util.*;

// @CrossOrigin(origins = "*")
// @RestController

// public class AccountControllerController {

//     @Autowired
//     private AccountService acservice;

//     @Autowired
//     AccountRepository accountRepository;

//     @GetMapping(value = {"/accounts", "/accounts/"})
//     public List<AccountDto> getAllAccounts() {
//         return acservice.getAllAccounts().stream().map(p -> convertToDto(p)).collect(Collectors.toList());
//     }
    
// }
