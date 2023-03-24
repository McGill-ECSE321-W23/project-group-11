package ca.mcgill.ecse321.ParkingManagement.dto;

import org.springframework.lang.NonNull;


public class AccountDto {

    //atributes
    
    @NonNull
    private String email;
    
    @NonNull
    private String password;
    

    private boolean logInStatus;//used to indicate login and logout status of an account

    //constructor
    public AccountDto(String email, String password, boolean logInStatus) {
        this.email = email;
        this.password = password;
        this.logInStatus = false;
    }
    //getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean getlogInStatus() {
        return logInStatus;
    }

    //setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogInStatus(boolean logInStatus) {
        this.logInStatus = logInStatus;
    }
}