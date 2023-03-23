package ca.mcgill.ecse321.ParkingManagement.dto;

import org.springframework.lang.NonNull;

public class AccountDto {

    //atributes
    
    @NonNull
    private String email;
    
    @NonNull
    private String password;
    
    private String token;//used to indicate login and logout

    //constructor
    public AccountDto(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }
    //getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    //setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }
}