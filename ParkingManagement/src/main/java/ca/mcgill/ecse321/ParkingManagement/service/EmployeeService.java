package ca.mcgill.ecse321.ParkingManagement.service;
import ca.mcgill.ecse321.ParkingManagement.dao.*;
import ca.mcgill.ecse321.ParkingManagement.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Transactional
    public List<Employee> getAllEmployees() {
        return toList(employeeRepository.findAll());
    }

    
    @Transactional
    public Employee createEmployee(Account account) throws Exception {
        if(account == null) {
            Exception e = new Exception("Can't create an employee with null account.");
            throw e;
        }
        if(employeeRepository.findEmployeeByAccount(account)!=null){
            Exception e = new Exception("employee already exists.");
            throw e;
        }
        Employee employee = new Employee();
        employee.setAccount(account);
        return employeeRepository.save(employee);
    }


    @Transactional
    public Employee getEmployeeByEmail(String email) throws Exception {
        if(email == null || email == "") {
            Exception e = new Exception("Can't find employee with empty email.");
            throw e;   
        }
        Account account = accountRepository.findAccountByEmail(email);
        if(account == null) {
            Exception e = new Exception("No account with this email.");
            throw e;
        }
        Employee employee = employeeRepository.findEmployeeByAccount(account);
        if(employee == null) {
            Exception e = new Exception("No employee with this account.");
            throw e;
        }
        return employee;
    }

    @Transactional
    public Employee deleteEmployee(Employee employee) throws Exception {
        Employee employee_dummy = null;
        if(employee == null) {
            Exception e = new Exception("Cant delete an employee without an account");
            throw e;
        }
        employeeRepository.deleteEmployeeByAccount(employee.getAccount());
        employee_dummy = employeeRepository.findEmployeeByAccount(employee.getAccount());
        if (employee_dummy != null) {
            Exception e = new Exception("Employee not deleted");
            throw e;
        }
        return employee_dummy;
    }

    private <T> List<T> toList(Iterable<T> iterable){
        List<T> resultList = new ArrayList<T>();
        for (T t : iterable) {
            resultList.add(t);
        }
        return resultList;
    }

}

