package ca.mcgill.ecse321.ParkingManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ca.mcgill.ecse321.ParkingManagement.service.*;
import ca.mcgill.ecse321.ParkingManagement.dto.*;

@CrossOrigin(origins = "*")
@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;
    
    
    @GetMapping(value ={"/price/temp/{sysInfoId}", "/price/temp/{sysInfoId}/"})
    public ResponseEntity<?> getTempSpotPrice(@RequestBody TempSpotDto spot, @PathVariable int sysInfoId){
        return new ResponseEntity<>(paymentService.getCalculatedPriceForSpot(spot,sysInfoId), HttpStatus.OK);
    }

    @GetMapping(value ={"/price/month/{sysInfoId}/", "/price/month/{sysInfoId}"})
    public ResponseEntity<?> getMonthlySpotPrice(@PathVariable int sysInfoId){
            return new ResponseEntity<>(paymentService.getPriceForMonthlySpot(sysInfoId), HttpStatus.OK);
    }

    @GetMapping(value ={"/price/service/{name}", "/price/service/{name}/"})
    public ResponseEntity<?> getServicePrice(@PathVariable String name) throws Exception{
        try{
            return new ResponseEntity<>(paymentService.getPriceForService(name), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping(value ={"/payment/{cardNumber}", "/payment/{cardNumber}/"})
    public ResponseEntity<?> getPaymentValidation(@PathVariable String cardNumber) throws Exception{
        String result = "";
        try{
            paymentService.validatePayment(cardNumber);
            result = "Payment Validated";
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
