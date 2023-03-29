package ca.mcgill.ecse321.ParkingManagement.service;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Manager createManager(Account account) throws Exception {
        if(account == null) {
            Exception e = new Exception("Can't create a manager with null account.");
            throw e;
        }

        Manager manager = new Manager();
        manager.setAccount(account);
        return manager;
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

    @Transactional
    public Manager deleteManager(Manager manager) throws Exception {
        Manager manager_dummy = null;
        if(manager == null) {
            Exception e = new Exception("Cant delete a manager without an account");
            throw e;
        }
        managerRepository.deleteManagerByAccount(manager.getAccount());
        manager_dummy = managerRepository.findManagerByAccount(manager.getAccount());
        if (manager_dummy != null) {
            Exception e = new Exception("Manager not deleted");
            throw e;
        }
        return manager_dummy;
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}
