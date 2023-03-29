package ca.mcgill.ecse321.ParkingManagement.service;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerService {
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Transactional
    public List<Manager> getAllManagers() {
        return toList(managerRepository.findAll());
    }

    @Transactional
    public Manager getManagerByEmail(String email) throws Exception {
        if(email == null || email == "") {
            Exception e = new Exception("Can't find manager with empty email.");
            throw e;   
        }
        Account account = accountRepository.findAccountByEmail(email);
        if(account == null) {
            Exception e = new Exception("No account with this email.");
            throw e;
        }
        Manager manager = managerRepository.findManagerByAccount(account);
        if(manager == null) {
            Exception e = new Exception("No manager with this account.");
            throw e;
        }
        return manager;
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
