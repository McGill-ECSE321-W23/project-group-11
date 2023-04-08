package ca.mcgill.ecse321.ParkingManagement.dto;

public class CustomerDto {
    
    public int id;
    public String account;

    /**
     * Dto constructor
     * @param id customer id
     * @param account email identifier for account
     * 
     */
    
    public CustomerDto(int id, String account) {
        this.id = id;
        this.account = account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return this.account;
    }

    public int getId() {
        return this.id;
    }
}
