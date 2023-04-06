package ca.mcgill.ecse321.ParkingManagement.dto;

import org.springframework.lang.NonNull;

import ca.mcgill.ecse321.ParkingManagement.model.Account;


public class AccountDto {

    //atributes
    
    @NonNull
    private String email;
    
    @NonNull
    private String password;
    

    private boolean loginStatus;//used to indicate login and logout status of an account

    //constructor
    public AccountDto(String email, String password, boolean loginStatus) {
        this.email = email;
        this.password = password;
        this.loginStatus = loginStatus;
    }
    //getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getloginStatus() {
        return loginStatus;
    }

    //setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogInStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
    public Account toModel() {
        Account a = new Account(email, password, loginStatus);
        return a;
    }
}