package ca.mcgill.ecse321.ParkingManagement.dto;

public class AccountDto {

    //atributes
    private String email;
    private String password;

    //constructor
    public AccountDto(String email, string password ) {
        this.email = email;
        this.password = password;
    }
    //getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
